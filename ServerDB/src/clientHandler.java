import sun.security.provider.certpath.Vertex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    public String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    public ClientHandler(Socket s, String name,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = false;
    }

    @Override
    public void run() {
        try {
            HistoryChat historyChat = new HistoryChat();
            PersonDB personDB = new PersonDB();
            String received;
            while (true)
            {
                String command = dis.readUTF();
                if (command.equals("signIn"))
                    getSignInInfo(personDB);

                else if (command.equals("signUp"))
                    getSignUpInfo(personDB);

                    if (isloggedin)
                {
                    System.out.println("Logged in");
                    break;
                }
            }

//            String recipient = dis.readUTF();
            System.out.println(historyChat.getHistory("client 0", "client 1"));
//            dos.writeUTF(historyChat.getHistory(name, recipient));

            while (isloggedin) {
                try {
                    String recipient = dis.readUTF();
                    received = dis.readUTF();

                    if (recipient.equals("upload"))
                    {
                        uploadChat(personDB, historyChat, received);
                    }

                    if (received.equals("logout"))
                    {
                        this.isloggedin = false;
                        this.s.close();
                        break;
                    }

                    for (ClientHandler mc : Server.ar) {
                        if (mc.name.equals(recipient) && mc.isloggedin == true)
                        {
                            mc.dos.writeUTF(this.name + " : " + received);
                            System.out.println("");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                this.dis.close();
                this.dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e)
        {

        }

    }

    public void getSignInInfo (PersonDB personDB)
    {
        try {
            String username = dis.readUTF();
            String pass = dis.readUTF();

            if (personDB.getPerson(username).equals(username) && personDB.getPass(pass).equals(pass))
                isloggedin = true;

        } catch (Exception e) {

        }
    }

    public void getSignUpInfo(PersonDB personDB)
    {
        try
        {
            String username = dis.readUTF();
            String pass = dis.readUTF();
            String firstname = dis.readUTF();
            String lastname = dis.readUTF();
            String email = dis.readUTF();
            Person person = new Person(username, pass, firstname, lastname, email);
            personDB.addPerson(person);
            isloggedin = true;
        }
        catch (IOException e)
        {

        }
        catch (Exception e)
        {

        }
    }

    public String encode(String string) {

        char Arr[] = new char[string.length() + 1];

        for (int i = 0; i < string.length(); i++)
            Arr[i] = string.charAt(i);

        for (int i = 0; i < string.length(); i++) {
            int ascii = Arr[i];
            ascii = ascii - 65;
            ascii = ascii + 10;
            ascii = ascii % 58;
            ascii = ascii + 65;
            Arr[i] = (char) ascii;
        }

        return String.valueOf(Arr);
    }

    public void uploadChat (PersonDB personDB, HistoryChat historyChat, String received)
    {
        try {
            String username = dis.readUTF();
            PersonHistory personHistory = new PersonHistory(received, this.name, username);
            historyChat.changeHistory(personHistory,  received);
        } catch (Exception e) {

        }
    }
}