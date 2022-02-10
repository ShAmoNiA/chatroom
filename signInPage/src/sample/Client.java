package sample;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    final static int ServerPort = 1234;
    Scanner scanner = new Scanner(System.in);
    FileInputStream fis;
    FileOutputStream fos;
    DataInputStream dis;
    DataOutputStream dos;
    Socket s;
    boolean loggedIn = false;

    String recievedMSG = "";

    public void sendMSG(String sentMSG)
    {
        try
        {
            dos.writeUTF(sentMSG);
            if (sentMSG.equals("logout"))
            {
                dis.close();
                dos.close();
                s.close();
                System.exit(0);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readMSG()
    {
        try
        {
            recievedMSG = dis.readUTF();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getRecievedMSG()
    {
        return this.recievedMSG;
    }
/*
    public String getHistory(String recipient) throws IOException, InterruptedException
    {
        System.out.println("AAAAAAAAAA");
            dos.writeUTF(recipient);
        System.out.println("AAAAAAAAAA");

        String chat = dis.readUTF();
        System.out.println(chat);
        return chat;

    }
*/


    public String coding(String string) {
        char Arr2[] = new char[58];
        char Arr3[] = new char[58];
        char Arr4[] = new char[58];

        char Arr[] = new char[string.length() + 1];

        for (int i = 0; i < string.length(); i++)
            Arr[i] = string.charAt(i);

        for (int i = 0; i < 58; i++) {
            Arr2[i] = (char) (i + 65);
            Arr3[i] = (char) (i + 65);
        }


        for (int i = 0; i < 58; i++) {
            int ascii = Arr2[i];
            ascii = ascii - 65;
            ascii = ascii + 10;
            ascii = ascii % 58;
            ascii = ascii + 65;
            Arr2[i] = (char) ascii;
        }


        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < 58; j++) {
                if (Arr2[j] == Arr[i]) {
                    Arr4[i] = Arr3[j];
                    break;
                }
            }
        }
        return String.valueOf(Arr4);
    }

    public Client()
    {
        try
        {
            InetAddress ip = InetAddress.getByName("localhost");

            s = new Socket(ip, ServerPort);

            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

        }catch (IOException ioException)
        {

        }
    }
}