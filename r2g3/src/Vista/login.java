package Vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField user;
	private JPasswordField contra;
	private JLabel logoElorrieta;
	private JLabel lblUser;
	private JLabel lblCon;
	private JButton btnLogin;

	/**
	 * Create the panel.
	 */
	public login() {
		setBounds(0, 0, 900, 700);
		setLayout(null);
		
		user = new JTextField();
		user.setBounds(421, 339, 134, 33);
		add(user);
		
		contra = new JPasswordField();
		contra.setBounds(421, 386, 134, 33);
		add(contra);
		
		lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(326, 348, 56, 14);
		add(lblUser);
		
		lblCon = new JLabel("Contraseña");
		lblCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCon.setBounds(326, 393, 86, 14);
		add(lblCon);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(379, 452, 105, 33);
		add(btnLogin);
		
		String fotopath="logo-elorrieta.jpg";
		ImageIcon foto = new ImageIcon(fotopath);
		logoElorrieta = new JLabel(foto);
		logoElorrieta.setBounds(79, 30, 768, 288);
		add(logoElorrieta);

	}

	public JTextField getUser() {
		return user;
	}

	public JPasswordField getContra() {
		return contra;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}
}
