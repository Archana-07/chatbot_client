package chatbox.client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SimpleChatClient {
	 private JTextField outgoing;
	 private PrintWriter writer;
	 public void go() {
	 setUpNetworking();
	 outgoing = new JTextField(20);
	 JButton sendButton = new JButton("Send");
	 sendButton.addActionListener(e -> sendMessage());
	 JPanel mainPanel = new JPanel();
	 mainPanel.add(outgoing);
	 mainPanel.add(sendButton);
	 JFrame frame = new JFrame("Ludicrously Simple Chat Client");
	 frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
	 frame.setSize(400, 100);
	 frame.setVisible(true);
	 frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	 }
	 
	private void sendMessage() {
		writer.write(outgoing.getText());
		writer.flush();
		outgoing.setText("");
		outgoing.requestFocus();
	}

	private void setUpNetworking() {
		try {
		InetSocketAddress netAddress=new InetSocketAddress("127.0.0.1", 5000);
		SocketChannel channel;
		channel = SocketChannel.open(netAddress);
		 writer=new PrintWriter(Channels.newWriter(channel, StandardCharsets.UTF_8));
		System.out.println("Connection established");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
