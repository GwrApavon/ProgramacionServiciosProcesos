package interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;

import util.Consumer;
import javax.swing.JTextField;

import chat.cliente.ClienteThread;

public class VentanaCliente{

	public JFrame frame;
	private JTextField textField;
	private ClienteThread ct;

	public void setCt(ClienteThread ct) {
		this.ct = ct;
	}

	/**
	 * Create the application.
	 */
	public VentanaCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		/**
		 * Botón para cerrar la conexión del cliente con el servidor y su respectiva ventana
		 */
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ct.getSocket() != null && ct.getStream() == true) ct.sendMsg("*");
				frame.dispose();
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 4;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		/**
		 * Listener para enviar el mesaje de la textbox de la interfaz una vez presionado el "Enter"
		 */
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub	
			}

			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					enviarMsg();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			
		});
		
		/**
		 * Botón que llama a enviar mensaje y envía el texto que haya en la textbox de la interfaz
		 */
		JButton enviar = new JButton("Enviar");
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ct.getSocket() != null)enviarMsg();
			}

			
		});
		
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 9;
		gbc_btnNewButton_1.gridy = 5;
		frame.getContentPane().add(enviar, gbc_btnNewButton_1);
	}

	public void agnadePanel(Consumer salachat) {
		GridBagConstraints gbc_salachat = new GridBagConstraints();
		gbc_salachat.gridheight = 4;
		gbc_salachat.gridwidth = 7;
		gbc_salachat.insets = new Insets(0, 0, 5, 5);
		gbc_salachat.fill = GridBagConstraints.BOTH;
		gbc_salachat.gridx = 1;
		gbc_salachat.gridy = 1;
		frame.getContentPane().add((Component) salachat, gbc_salachat);
		
	}
    
	/**
	 * Método que llama al sendMsg del hilo
	 */
	private void enviarMsg() {
		String msg = textField.getText().toString();
		ct.sendMsg(msg);
		if(msg.equals("*")) frame.dispose();
		else textField.setText("");
	}
}
