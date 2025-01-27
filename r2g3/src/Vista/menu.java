package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;

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
		btnConsultarHorario.setBounds(371, 251, 159, 41);
		add(btnConsultarHorario);
		
		btnOtrosHorarios = new JButton("Ver otros horarios");
		btnOtrosHorarios.setBounds(371, 317, 159, 41);
		add(btnOtrosHorarios);
		
		btnReuniones = new JButton("Ver reuniones");
		btnReuniones.setBounds(371, 382, 159, 41);
		add(btnReuniones);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(717, 39, 128, 32);
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
