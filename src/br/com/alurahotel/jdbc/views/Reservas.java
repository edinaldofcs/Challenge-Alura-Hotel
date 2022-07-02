package br.com.alurahotel.jdbc.views;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import br.com.alurahotel.jdbc.controller.ReservasController;
import br.com.alurahotel.jdbc.modelo.Hospede;
import br.com.alurahotel.jdbc.modelo.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Reservas extends JFrame {

	private static final long serialVersionUID = 7767045887526242900L;
	private JPanel contentPane;
	private JTextField Valor;
	private Double valorFinal;

	private ReservasController reservaController;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//					Date data = formato.parse("2000-01-01");
//					Reservas frame = new Reservas(new Reserva(data, data, 0.0, "", 0));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Reservas(Reserva reserva) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("../imagens/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		this.reservaController = new ReservasController();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		JDateChooser Entrada = new JDateChooser();
		Entrada.setBounds(88, 166, 235, 33);
		panel.add(Entrada);

		JLabel lblCheckIn = new JLabel("Data de Check In");
		lblCheckIn.setBounds(88, 142, 133, 14);
		lblCheckIn.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("Data de Check Out");
		lblCheckOut.setBounds(88, 210, 133, 14);
		lblCheckOut.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblCheckOut);

		JDateChooser Saida = new JDateChooser();
		Saida.setBounds(88, 234, 235, 33);
		Saida.getCalendarButton().setBackground(Color.WHITE);
		panel.add(Saida);

		Saida.getDateEditor().addPropertyChangeListener((PropertyChangeListener) new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {

					if (Entrada.getDate() != null)
						calcularDiaria(Entrada, Saida);

				}
			}

		});
		Entrada.getDateEditor().addPropertyChangeListener((PropertyChangeListener) new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {

					if (Saida.getDate() != null)
						calcularDiaria(Entrada, Saida);

				}
			}
		});

		Valor = new JTextField();
		Valor.setBounds(88, 303, 235, 33);
		Valor.setForeground(Color.BLACK);
		Valor.setEnabled(false);
		panel.add(Valor);
		Valor.setColumns(10);

		JLabel lblCheckOut_1 = new JLabel("Valor da Reserva");
		lblCheckOut_1.setBounds(88, 278, 133, 14);
		lblCheckOut_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblCheckOut_1);

		JComboBox FormaPago = new JComboBox();
		FormaPago.setBounds(88, 373, 235, 33);
		FormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		FormaPago
				.setModel(new DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Cartão de Débito", "Boleto" }));
		panel.add(FormaPago);

		JLabel fPagamento = new JLabel("Forma de pagamento");
		fPagamento.setBounds(88, 347, 151, 24);
		fPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(fPagamento);

		JLabel lblSistemaReserva = new JLabel("Sistema de Reservas");
		lblSistemaReserva.setBounds(108, 93, 199, 42);
		lblSistemaReserva.setForeground(new Color(65, 105, 225));
		lblSistemaReserva.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblSistemaReserva);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date data = formato.parse("2000-01-01");
			String validar = data.toString().substring(data.toString().length() - 4, data.toString().length());
			String inputData = reserva.getData_entrada().toString().substring(
					reserva.getData_entrada().toString().length() - 4, reserva.getData_entrada().toString().length());
			;
			String validar2 = data.toString().substring(data.toString().length() - 4, data.toString().length());
			String inputData2 = reserva.getData_saida().toString().substring(
					reserva.getData_saida().toString().length() - 4, reserva.getData_saida().toString().length());
			;

			if (Integer.parseInt(validar) == Integer.parseInt(inputData)
					&& Integer.parseInt(validar2) == Integer.parseInt(inputData2)) {
				Entrada.setDate(null);
				Saida.setDate(null);
			} else {
				Entrada.setDate(reserva.getData_entrada());
				Saida.setDate(reserva.getData_saida());
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		if (reserva.getValor() == 0) {
			Valor.setText(null);
		} else {
			String val = "" + Valor.getText();
			Valor.setText(val);
		}

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (reserva.getId() > 0) {
					if (Valor.getText().length() > 0) {											
						Reserva res = new Reserva(Entrada.getDate(), Saida.getDate(), valorFinal,
								FormaPago.getSelectedItem().toString(), reserva.getId());
						salvar(res, res.getId());
						dispose();
					} else {
						Erro erro = new Erro("Preencha todos os campos", 50);
						erro.setVisible(true);
					}
					
				} else {

					if (Valor.getText().length() > 0) {
						Reserva reserva = new Reserva(Entrada.getDate(), Saida.getDate(), valorFinal,
								FormaPago.getSelectedItem().toString(), 0);
						salvar(reserva, 0);
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
						Date data = null;
						try {
							data = formato.parse("2000-01-01");
						} catch (ParseException e1) {
							Erro erro = new Erro("Ocorrreu um erro inesperado", 50);
							erro.setVisible(true);
						}
						Hospede hos = new Hospede("", "", data, "", 0, reserva.getId(), 0);						
						RegistroHospede hospede = new RegistroHospede(hos);
						hospede.setVisible(true);
						dispose();
					} else {
						Erro erro = new Erro("Preencha todos os campos", 50);
						erro.setVisible(true);
					}
				}

			}
		});

		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(190, 436, 133, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("../imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);

	}

	private void calcularDiaria(JDateChooser dataEntrada, JDateChooser dataSaida) {
		SimpleDateFormat dcn = new SimpleDateFormat("yyyyMMdd");
		int entrada = Integer.parseInt(dcn.format(dataEntrada.getDate()));
		int saida = Integer.parseInt(dcn.format(dataSaida.getDate()));
		valorFinal = (saida - entrada) * 40.00;
		if (valorFinal > 0.0) {
			Valor.setText("R$ " + valorFinal.toString());
		} else {
			Valor.setText("");
		}
	}

	private void salvar(Reserva reservar, int id) {		
		if (!Valor.getText().equals("")) {
			this.reservaController.inserir(reservar);
			if(id > 0) {
				Sucesso exito = new Sucesso(reservar.getId(), "Reserva Alterada com sucesso", 40);
				exito.setVisible(true);
			}else {
				Erro erro = new Erro("Salvo com sucesso!", 80);
				erro.setVisible(true);				
			}
		} else {
			Erro erro = new Erro("Não foi possível finalizar a reserva", 10);
			erro.setVisible(true);
		}
	}
}
