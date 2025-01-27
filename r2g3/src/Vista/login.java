package Vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
		user.setBounds(421, 339, 123, 20);
		add(user);
		
		contra = new JPasswordField();
		contra.setBounds(421, 386, 123, 20);
		add(contra);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(325, 342, 46, 14);
		add(lblUser);
		
		lblCon = new JLabel("Contraseña");
		lblCon.setBounds(325, 389, 86, 14);
		add(lblCon);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(391, 437, 89, 23);
		add(btnLogin);
		
		String fotopath="logo-elorrieta.jpg";
		ImageIcon foto = new ImageIcon(fotopath);
		logoElorrieta = new JLabel(foto);
		logoElorrieta.setBounds(89, 30, 758, 272);
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
