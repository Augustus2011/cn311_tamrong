
//io
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

//network
import java.net.Socket;

//util
import java.util.ArrayList;


public class Client extends javax.swing.JFrame {

    Source OOP = new Source();
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();

    Boolean isConnected = false;

    Socket theSocket;
    BufferedReader theReader;
    PrintWriter theWriter;

    public void ListenThread() {
        Thread IncomingReader = new Thread(new Message());
        IncomingReader.start();
    }

    public void userAdd(String data) {
        users.add(data);
    }

    public void userRemove(String data) {
        sentMessage.append(data + " is now offline.\n");
    }

    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
    }

    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            theWriter.println(bye);
            theWriter.flush();
        } catch (Exception e) {
        }
    }

    public void Disconnect() {
        try {
            sentMessage.append("ยกเลิกการเชื่อมต่อ \n");
            theSocket.close();
        } catch (Exception ex) {

        }
        isConnected = false;
        client_Name.setEditable(true);

    }

    public Client() {
        initComponents();
    }

    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sentMessage = new javax.swing.JTextArea();
        Message = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        client_Name = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        sentMessage.setBackground(new java.awt.Color(240, 240, 240));
        sentMessage.setColumns(20);
        sentMessage.setFont(new java.awt.Font("TimesRoman", 1, 11));
        sentMessage.setRows(5);
        jScrollPane1.setViewportView(sentMessage);

        Message.setFont(new java.awt.Font("TimesRoman", 1, 11));
        Message.setText("พิมข้อความ");
        Message.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Message.setBackground(new java.awt.Color(240,240,240));
        Message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MessageKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MessageKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MessageKeyTyped(evt);
            }
        });
        
        

        jPanel2.setBackground(new java.awt.Color(240, 240, 240));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/plug-button.png")));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disConnectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isConnectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isConnectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(5, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/connect-button.png")));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isConnnectedMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isConnnectedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isConnnectedMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        client_Name.setBackground(new java.awt.Color(240, 240, 240));
        client_Name.setFont(new java.awt.Font("TimesRoman", 1, 11));
        client_Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        client_Name.setText("ชื่อของคุณ");
        client_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        
        client_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                client_NameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                client_NameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                client_NameKeyTyped(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/people icon.png")));

        jPanel4.setBackground(new java.awt.Color(240, 240, 240));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/plane-button.png")));
        
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SendMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Message)
                        .addComponent(jPanel4)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup()
                                    .addComponent(jLabel5)
                                    .addGap(23, 23, 23))
                                .addComponent(client_Name)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                ))
                                .addGap(16, 16, 16)
                
                        )))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(client_Name)
                    .addComponent(jPanel3)
                    .addComponent(jPanel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Message)
                            .addComponent(jPanel4))
                        .addContainerGap(16, Short.MAX_VALUE))))
                        
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void isConnnectedMouseClicked(java.awt.event.MouseEvent evt) {

        if (client_Name.getText().equals("")) {
            client_Name.setText("ยังไม่มีชื่อเลย");
        } else if (client_Name.getText().equals("ชื่อของคุณ")) {
            client_Name.setText("เปลี่ยนชื่อของคุณก่อนน!");
        } else {
            if (!isConnected) {
                username = client_Name.getText();
                client_Name.setEditable(false);
        
                try {
                    theSocket = new Socket(address, 2222);
                    InputStreamReader streamreader = new InputStreamReader(theSocket.getInputStream());
                    theReader = new BufferedReader(streamreader);
                    theWriter = new PrintWriter(theSocket.getOutputStream());
                    theWriter.println(username + ": พบการเชื่อมต่อ :Connect");
                    theWriter.flush();
                    isConnected = true;
                } catch (Exception ex) {
                    sentMessage.append("ไม่สามรถเชื่อมต่อได้โปรดลองอีกครั้ง \n");
                }
        
                ListenThread();
            } else {
                sentMessage.append("คุณ เชื่อมต่อได้เเล้ว \n");
            }
        }
        


    }

    private void disConnectMouseClicked(java.awt.event.MouseEvent evt) {
        
        sendDisconnect();
        Disconnect();
    }

    private void SendMouseClicked(java.awt.event.MouseEvent evt) {

        if (Message.getText().equals("พิมข้อความ")) {
            Message.requestFocus();
        }
        else {
            try {
                theWriter.println(username + " : " + Message.getText() + ":" + "Chat");
                theWriter.flush();
            } catch (Exception ex) {
            }
            Message.setText("");
            Message.requestFocus();
        }

        Message.setText("พิมข้อความ");
        Message.requestFocus();
    }

    private void MessageKeyPressed(java.awt.event.KeyEvent evt) {
        if (Message.getText().equals("พิมข้อความ"))
            Message.setText("");
    }

    private void client_NameKeyPressed(java.awt.event.KeyEvent evt) {
       
        if (client_Name.getText().equals("ชื่อของคุณ"))
            client_Name.setText("");
    }

    private void isConnnectedMouseEntered(java.awt.event.MouseEvent evt) {
        
        OOP.changeColor(jPanel3);
    }

    private void isConnnectedMouseExited(java.awt.event.MouseEvent evt) {
        
        OOP.NormalWhite(jPanel3);
        
    }

    private void isConnectMouseEntered(java.awt.event.MouseEvent evt) {
        
        OOP.changeColor(jPanel2);
    }

    private void isConnectMouseExited(java.awt.event.MouseEvent evt) {
        
        OOP.NormalWhite(jPanel2);
    }

    private void client_NameKeyTyped(java.awt.event.KeyEvent evt) {
        if (client_Name.getText().equals("ชื่อของคุณ")) {
            client_Name.setText("");
        }
    }

    private void client_NameKeyReleased(java.awt.event.KeyEvent evt) {
        if (client_Name.getText().equals("")) {
            client_Name.setText("ชื่อของคุณ");
        } else {
            String name = client_Name.getText();
            String result = name.substring(0, 1).toUpperCase() + name.substring(1);
            client_Name.setText(result);
        }        
    }

    private void SendMouseEntered(java.awt.event.MouseEvent evt) {
       
        OOP.changeColor(jPanel4);
    }

    private void SendMouseExited(java.awt.event.MouseEvent evt) {
      
        OOP.NormalWhite(jPanel4);
    }

    private void MessageKeyReleased(java.awt.event.KeyEvent evt) {
        String text = Message.getText();
        if (text.equals("")) {
            Message.setText("พิมข้อความ");
        } else {
            String name = text;
            String result = name.substring(0, 1).toUpperCase() + name.substring(1);
            Message.setText(result);
        }
    }

    private void MessageKeyTyped(java.awt.event.KeyEvent evt) {

    }


    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
    
    private javax.swing.JTextField Message;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField client_Name;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea sentMessage;
    
public class Message implements Runnable {

        public void run() {
            String[] data;
            String stream;

            try {
                while ((stream = theReader.readLine()) != null) {
                    data = stream.split(":");
                    if (data[2].equals("Chat")) {
                        sentMessage.append(data[0] + ":" + data[1] + "\n");
                        sentMessage.setCaretPosition(sentMessage.getDocument().getLength());
                    } else if (data[2].equals("Connnect")) {
                        sentMessage.removeAll();
                        userAdd(data[0]);
                    } else if (data[2].equals("Disconnect")) {
                        userRemove(data[0]);
                    } else if (data[2].equals("Done")) {
                        writeUsers();
                        users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
