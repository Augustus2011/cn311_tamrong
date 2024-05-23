//io

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

//network
import java.net.ServerSocket;
import java.net.Socket;

//util
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

//data
import java.text.SimpleDateFormat;




public class Server extends javax.swing.JFrame {

    Source OOP = new Source();
    ArrayList clientOutputStreams;
    ArrayList<String> users;


    private javax.swing.JTextArea dataStored;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel server;

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;
        PrintWriter client;

        public ClientHandler(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
            }

        }

        public void run() {
            String message, chat = "Chat";
            String[] data;
            try {
                
                while ((message = reader.readLine()) != null) {
                    
                    dataStored.append("Received: " + message + "\n");
                    data = message.split(":");
        
                    for (String token : data) {
                        dataStored.append(token + "\n");
                    }
        
                    
                    if (data[2].equals("Connect")) {
                        tellEveryone(data[0] + ":" + data[1] + ":" + chat);
                        userAdd(data[0]);
                    } else if (data[2].equals("Disconnect")) {
                        tellEveryone(data[0] + ":ได้ออกจากห้อง" + ":" + chat);
                        userRemove(data[0]);
                    } else if (data[2].equals("Chat")) {
                        tellEveryone(message);
                    } else {
                        dataStored.append("ไม่สามารถทำงานได้ \n");
                    }
                }
            } catch (Exception ex) {
                dataStored.append("หลุดการเชื่อมต่อ \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            }
        }
        

        }

    public Server() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataStored = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        server = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255,255));

        dataStored.setColumns(20);
        dataStored.setFont(new java.awt.Font("TimesRoman", 1, 11));
        dataStored.setRows(5);
        dataStored.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(dataStored);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/start-button.png")));
        jLabel2.setText("Start");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/stop-button.png")));
        jLabel3.setText("Stop");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        server.setBackground(new java.awt.Color(153, 153, 153));
        server.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/chat icon.png")));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup()
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup()
                    .addComponent(server)
                    .addComponent(jPanel2)
                    .addComponent(jPanel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup()
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup()
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(server)
                        .addGap(64, 64, 64)
                        .addComponent(jPanel2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3)
                        .addGap(64, 64, 64)
                        ))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
            .addComponent(jPanel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
            .addComponent(jPanel1)
        );

        pack();
    }

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        Thread starter = new Thread(new StartServer());
        starter.start();

        dataStored.append("Server เปิดเเล้ว...\n");
    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt){
        try {
            Thread.sleep(1000);
            tellEveryone("Server:กำลังปิด \n:Chat");
            dataStored.append("Server ปิดเเล้วจ้า... \n");
    
            
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String logFilename = "logs/"+ timestamp + ".txt";
            
            try (PrintWriter out = new PrintWriter(logFilename)) {
                out.println(dataStored.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
    
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    
        dataStored.setText("");

    }

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {
        OOP.changeColor(jPanel2);
    }

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {
        OOP.normalColor(jPanel2);
    }

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {
        OOP.changeColor(jPanel3);
    }

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {
        OOP.normalColor(jPanel3);
    }
    
    public static void main(String args[]) {
        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    public void userAdd(String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.add(name);
        dataStored.append(name + "ได้เข้ามา \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token : tempList) {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }

    public void userRemove(String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token : tempList) {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }

    public void tellEveryone(String message) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                dataStored.append("ส่งข้อความ: " + message + "\n");
                writer.flush();
                dataStored.setCaretPosition(dataStored.getDocument().getLength());

            } catch (Exception ex) {
            }
        }
    }


 public class StartServer implements Runnable{

        public void run() {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();

            try {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);
                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    
                    listener.start();
                    
                    dataStored.append("พบการเชื่อมค่อ \n");
                }
            } catch (Exception ex) {
            }
        }
    }

}
