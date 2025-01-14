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
		setBounds(0, 0, 500, 400);
		setLayout(null);
		
		btnConsultarHorario = new JButton("Ver horario");
		btnConsultarHorario.setBounds(163, 65, 128, 23);
		add(btnConsultarHorario);
		
		btnOtrosHorarios = new JButton("Ver otros horarios");
		btnOtrosHorarios.setBounds(163, 121, 128, 23);
		add(btnOtrosHorarios);
		
		btnReuniones = new JButton("Ver reuniones");
		btnReuniones.setBounds(163, 186, 128, 23);
		add(btnReuniones);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(325, 11, 115, 23);
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
