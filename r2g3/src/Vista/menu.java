package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

public class menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnDesconectar;
	private JButton btnConsultarHorario;
	private JButton btnOtrosHorarios;
	private JButton btnReuniones;

	/**
	 * Create the panel.
	 */
	public menu() {
		setBounds(0, 0, 900, 700);
		setLayout(null);
		
		btnConsultarHorario = new JButton("Ver horario");
		btnConsultarHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarHorario.setBounds(371, 251, 170, 49);
		add(btnConsultarHorario);
		
		btnOtrosHorarios = new JButton("Ver otros horarios");
		btnOtrosHorarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOtrosHorarios.setBounds(371, 317, 170, 49);
		add(btnOtrosHorarios);
		
		btnReuniones = new JButton("Ver reuniones");
		btnReuniones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReuniones.setBounds(371, 393, 170, 49);
		add(btnReuniones);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDesconectar.setBounds(710, 32, 143, 41);
		add(btnDesconectar);

	}

	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}
	public JButton getBtnConsultarHorario() {
		return btnConsultarHorario;
	}

	public JButton getBtnOtrosHorarios() {
		return btnOtrosHorarios;
	}

	public JButton getBtnReuniones() {
		return btnReuniones;
	}


}
