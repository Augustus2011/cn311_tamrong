import java.awt.Color;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends javax.swing.JFrame {
    Source OOP=new Source();
    String username,addr="localhost";
    ArrayList<String> users = new ArrayList();
    Boolean isConnected=false;
    Socket theSocket;
    BufferedReader theReader;
    PrintWriter theWriter;
    
    public void ListenThread(){
        Thread IncomingReader=new Thread(new Message());
        IncomingReader.start();
    }
    public void userAdd(String data){
        users.add(data);
    }
    public void userRemove(String data){
        sentMessage.append(data+" ofline jaa.\n");
    }
    public void writeUsers(){
        String[] tempList=new String[(users.size())];
        users.toArray(tempList);
        for (String token:tempList){
            
        }
    }
    public void sendDisconnect(){
        String bye=(username+":Disconnect.\n");
        try{
            theWriter.println(bye);
            theWriter.flush();
        }
        catch (Exception e){
            sentMessage.append("no connected.\n");
        }
    }
    public void Disconnect(){
        
    }


    private javax.swing.JTextField Message;
    private javax.swing.JLabel Send;
    private javax.swing.JTextField client_Name;
    private javax.swing.JLabel disConnect;
    private javax.swing.JLabel isConnnected;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea sentMessage;

    public class Message implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream;

            try {
                while (!(stream = theReader.readLine()).equals(null)) {
                    data = stream.split(":");

                    switch (data[2]) {
                        case "Chat":
                            sentMessage.append(data[0] + ":" + data[1] + "\n");
                            sentMessage.setCaretPosition(sentMessage.getDocument().getLength());
                            break;
                        case "Connnect":
                            sentMessage.removeAll();
                            userAdd(data[0]);
                            break;
                        case "Disconnect":
                            userRemove(data[0]);
                            break;
                        case "Done":
                            writeUsers();
                            users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}

