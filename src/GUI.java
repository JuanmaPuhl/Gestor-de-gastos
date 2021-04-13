import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class GUI {

	/**Componentes GUI*/
	private JFrame frame;
	private JTable table_ultimasDiez;
	private JPanel panel_insercion;
	private JPanel panel_general;
	private JTextField textField_monto;
	private JTextField textField_desc;
	private JComboBox<String> comboBox_anio;
	private JComboBox<String> comboBox_dia;
	private JComboBox<String> comboBox_mes;
	private JScrollPane scrollPane;
	private JPanel panel_saldo;
	private JLabel lblSaldo;
	private JLabel lbl_saldoActual;
	private JLabel lblUltimasDiez;
	private JTextField textField_montoInf;
	private JTextField textField_montoSup;
	private JTable table_historial;
	private JPanel panel_historial;
	private JPanel panel_config;
	private JPanel panel_acercaDe;
	private JComboBox<String> comboBox_anioHistorial;
	private JPanel menu_lateral;
	private JPanel btn_menu;
	private JLabel lblMenu;
	private JPanel btn_home;
	private JLabel label;
	private JLabel lblInicio;
	private JPanel btn_add;
	private JLabel label_1;
	private JLabel lblNuevoMovimiento;
	private JPanel btn_historial;
	private JLabel label_2; 
	private JLabel lblHistorial;
	private JPanel btn_config;
	private JLabel label_3;
	private JLabel lblConfiguracion;
	private JPanel btn_about;
	private JLabel label_4;
	private JPanel panel_3;
	private JLabel lblConfiguracin;
	private JLabel lblAcercaDe;
	private JPanel btn_guardar;
	private JLabel lblGuardarCambios;
	private JPanel btn_reestablecer;
	private JLabel lblReestablecerPredet;
	private JLabel lblEsquemaDeColor;
	private JComboBox<String> comboBox_3;
	private JPanel btn_eliminar;
	private JLabel lblEliminarDatos;
	private JPanel panel_2;
	private JLabel lblAcercaDe_1;
	private JLabel lblAbout1;
	private JLabel lblAbout2;
	private JLabel lblAbout3;
	private JLabel lblAbout4;
	private JLabel lblAbout5;
	private JLabel lblAbout6;
	private JPanel panel_1;
	private JLabel lblHistorial_1;
	private JScrollPane scrollPane_historial;
	private JSeparator separator_1;
	private JLabel lblFiltrarPor;
	private JRadioButton rdbtn_IngresoHistorial;
	private JRadioButton rdbtn_GastoHistorial;
	private JRadioButton rdbtn_PrestamoHistorial;
	private JComboBox<String> comboBox_diaHistorial;
	private JComboBox<String> comboBox_mesHistorial;
	private JLabel lblTipo_1;
	private JSeparator separator_2;
	private JLabel lblFecha_1;
	private JLabel lblMontoMaximo;
	private JLabel lblMontoMinimo;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JPanel panel;
	private JRadioButton radiobtn_Ingreso;
	private JRadioButton radiobtn_Gasto;
	private JRadioButton radiobtn_Prestamo;
	private JLabel lblMonto;
	private JLabel lblDescripcion;
	private JLabel lblTipo;
	private JLabel lblFecha;
	private JPanel btn_aceptar;
	private JLabel lblAceptar;
	private JPanel btn_cancelar;
	private JLabel lblCancelar;
	private JLabel lblNuevaInsercion;
	private JSeparator separator;
	private JPanel btn_filtro;
	
	/**Variables Mias*/
	private int MAXFILAS=10,MAXCOLUMNAS=4;
	private Color color_btn_lateral=Color.GRAY;
	private Color color_header=Color.DARK_GRAY;
	private Color color_table_header=Color.DARK_GRAY;
	private Color color_btn_lateral_selected=Color.LIGHT_GRAY;
	private Color color_table_selected=Color.LIGHT_GRAY;
	private boolean selected=false;
	private Logica logica=new Logica();
	private String esquema="";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}

	public void enableComponents(Container container, boolean enable) {
	    Component[] components = container.getComponents();
	    for (Component component : components) {
	    	if(component instanceof JLabel)
	    		component.setEnabled(true);
	    	else
	    		component.setEnabled(enable);
	        if (component instanceof Container) {
	            enableComponents((Container)component, enable);
	        }
	    }
	}
	

	private void initialize() {
		frame = new JFrame("DoubleG v2.0");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Resources/icono.png")));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		logica.checkFiles();
		frame.setResizable(false);
		logica.recuperarAux();
		logica.saveAux();
		esquema=logica.leerEsquema();
		cambiarEsquemaColor(esquema);
		logica.guardarEsquema(esquema);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Pongo el look and feel de windows
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		
		/**============CODIGO DE CREACION DEL MENU LATERAL============*/
		
		menu_lateral = new JPanel();
		menu_lateral.setBounds(0, 0, 40, 472);
		frame.getContentPane().add(menu_lateral);
		menu_lateral.setBackground(color_btn_lateral);
		menu_lateral.setLayout(null);
		
		/**============CODIGO DE CREACION DEL BOTON MENU============*/
		
		btn_menu = new JPanel();
		btn_menu.setBounds(0, 0, 40, 40);
		menu_lateral.add(btn_menu);
		btn_menu.setBackground(color_btn_lateral);
		btn_menu.setLayout(null);
		btn_menu.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_menu.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_menu.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_menu.requestFocus();
				if(!selected) {
					menu_lateral.setBounds(0,0,200,472);
					selected=true;
					enableComponents(panel_general,false);
					enableComponents(panel_insercion,false);
					enableComponents(panel_historial,false);
					enableComponents(panel_acercaDe,false);
					enableComponents(panel_config,false);
				}
				else {
					menu_lateral.setBounds(0,0,40,472);
					enableComponents(panel_general,true);
					enableComponents(panel_insercion,true);
					enableComponents(panel_historial,true);
					enableComponents(panel_acercaDe,true);
					enableComponents(panel_config,true);
					selected=false;
					
				}
			}
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		lblMenu = new JLabel("");
		lblMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setBounds(0, 0, 40, 40);
		lblMenu.setIcon(new ImageIcon(getClass().getResource("/Resources/icons8_Menu_25px_1.png")));
		btn_menu.add(lblMenu);
		
		/**============CODIGO DE CREACION DEL BOTON HOME============*/
		
		btn_home = new JPanel();
		btn_home.setBounds(0, 52, 200, 40);
		btn_home.setBackground(color_btn_lateral);
		menu_lateral.add(btn_home);
		btn_home.setLayout(null);
		btn_home.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_home.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_home.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_home.requestFocus();
				ocultarPaneles();
				panel_general.setVisible(true);				
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		
		label = new JLabel("");
		label.setBounds(0, 0, 40, 40);
		btn_home.add(label);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("Resources/icons8_Home_25px_1.png")));
		
		lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(50, 0, 130, 40);
		btn_home.add(lblInicio);
		
		/**============CODIGO DE CREACION DEL BOTON INSERCION============*/

		btn_add = new JPanel();
		btn_add.setBackground(color_btn_lateral);
		btn_add.setBounds(0, 92, 200, 40);
		menu_lateral.add(btn_add);
		btn_add.setLayout(null);
		btn_add.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_add.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_add.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_add.requestFocus();
				ocultarPaneles();
				panel_insercion.setVisible(true);
				limpiarInsercion();
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		
		
		label_1 = new JLabel("");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(getClass().getResource("Resources/icons8_Add_25px.png")));
		label_1.setBounds(0, 0, 40, 40);
		btn_add.add(label_1);
		
		lblNuevoMovimiento = new JLabel("Nuevo Movimiento");
		lblNuevoMovimiento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNuevoMovimiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoMovimiento.setForeground(Color.WHITE);
		lblNuevoMovimiento.setBounds(50, 0, 130, 40);
		btn_add.add(lblNuevoMovimiento);
		
		/**============CODIGO DE CREACION DEL BOTON HISTORIAL============*/
		
		btn_historial = new JPanel();
		btn_historial.setBackground(color_btn_lateral);
		btn_historial.setBounds(0, 131, 200, 40);
		menu_lateral.add(btn_historial);
		btn_historial.setLayout(null);
		btn_historial.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_historial.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_historial.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_historial.requestFocus();
				ocultarPaneles();
				panel_historial.setVisible(true);
				limpiarHistorial();
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(getClass().getResource("Resources/icons8_Schedule_25px.png")));
		label_2.setBounds(0, 0, 40, 40);
		btn_historial.add(label_2);
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHistorial.setForeground(Color.WHITE);
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setBounds(50, 0, 140, 40);
		btn_historial.add(lblHistorial);
		
		/**============CODIGO DE CREACION DEL BOTON CONFIGURACION============*/
		
		btn_config = new JPanel();
		btn_config.setBackground(color_btn_lateral);
		btn_config.setBounds(0, 170, 200, 40);
		menu_lateral.add(btn_config);
		btn_config.setLayout(null);
		btn_config.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_config.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_config.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_config.requestFocus();
				ocultarPaneles();
				panel_config.setVisible(true);
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(getClass().getResource("Resources/icons8_Settings_25px.png")));
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 0, 40, 40);
		btn_config.add(label_3);
		
		lblConfiguracion = new JLabel("Configuracion");
		lblConfiguracion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblConfiguracion.setForeground(Color.WHITE);
		lblConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracion.setBounds(50, 0, 130, 40);
		btn_config.add(lblConfiguracion);
		
		/**============CODIGO DE CREACION DEL BOTON ACERCA DE============*/
		
		btn_about = new JPanel();
		btn_about.setBackground(color_btn_lateral);
		btn_about.setBounds(0, 208, 200, 40);
		menu_lateral.add(btn_about);
		btn_about.setLayout(null);
		btn_about.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_about.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_about.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				btn_about.requestFocus();
				ocultarPaneles();
				panel_acercaDe.setVisible(true);
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		
		label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(getClass().getResource("/Resources/icons8_About_25px.png")));
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 0, 40, 40);
		btn_about.add(label_4);
				
		lblAcercaDe = new JLabel("Acerca de");
		lblAcercaDe.setForeground(Color.WHITE);
		lblAcercaDe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setBounds(50, 0, 130, 40);
		btn_about.add(lblAcercaDe);
		
		/**============CODIGO DE CREACION DEL PANEL CONFIGURACION============*/
		
		panel_historial = new JPanel();
		panel_historial.setBounds(0, 0, 800, 472);
		frame.getContentPane().add(panel_historial);
		panel_historial.setLayout(null);
		panel_historial.setVisible(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(color_header);
		panel_1.setBounds(42, 28, 748, 404);
		panel_historial.add(panel_1);
		panel_1.setLayout(null);
		
		lblHistorial_1 = new JLabel("Historial");
		lblHistorial_1.setBounds(10, 11, 433, 31);
		lblHistorial_1.setForeground(Color.WHITE);
		lblHistorial_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHistorial_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblHistorial_1);
		
		table_historial = new JTable();
		
		
		scrollPane_historial = new JScrollPane(table_historial);
		scrollPane_historial.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_historial.setBounds(20, 53, 411, 338);
		panel_1.add(scrollPane_historial);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(453, 11, 2, 380);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_1.add(separator_1);
		
		lblFiltrarPor = new JLabel("Filtrar por");
		lblFiltrarPor.setBounds(465, 23, 271, 19);
		lblFiltrarPor.setForeground(Color.WHITE);
		lblFiltrarPor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFiltrarPor.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblFiltrarPor);
		
		rdbtn_IngresoHistorial = new JRadioButton("Ingreso");
		rdbtn_IngresoHistorial.setBounds(543, 95, 109, 23);
		rdbtn_IngresoHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbtn_IngresoHistorial.setForeground(Color.WHITE);
		rdbtn_IngresoHistorial.setOpaque(false);
		rdbtn_IngresoHistorial.setSelected(true);
		panel_1.add(rdbtn_IngresoHistorial);
		
		rdbtn_GastoHistorial = new JRadioButton("Gasto");
		rdbtn_GastoHistorial.setBounds(543, 121, 109, 23);
		rdbtn_GastoHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbtn_GastoHistorial.setForeground(Color.WHITE);
		rdbtn_GastoHistorial.setOpaque(false);
		rdbtn_GastoHistorial.setSelected(true);
		panel_1.add(rdbtn_GastoHistorial);
		
		rdbtn_PrestamoHistorial = new JRadioButton("Prestamo");
		rdbtn_PrestamoHistorial.setBounds(543, 147, 109, 23);
		rdbtn_PrestamoHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbtn_PrestamoHistorial.setForeground(Color.WHITE);
		rdbtn_PrestamoHistorial.setOpaque(false);
		rdbtn_PrestamoHistorial.setSelected(true);
		panel_1.add(rdbtn_PrestamoHistorial);
		
		comboBox_diaHistorial = new JComboBox<String>();
		comboBox_diaHistorial.setBounds(491, 218, 46, 20);
		addDays(comboBox_diaHistorial,"Diciembre","2018");
		panel_1.add(comboBox_diaHistorial);
		
		comboBox_diaHistorial.setSelectedIndex(-1);
		
		
		comboBox_mesHistorial = new JComboBox<String>();
		comboBox_mesHistorial.setBounds(547, 218, 75, 20);
		//comboBox_mesHistorial.set
		panel_1.add(comboBox_mesHistorial);
		comboBox_mesHistorial.addItem("Enero");
		comboBox_mesHistorial.addItem("Febrero");
		comboBox_mesHistorial.addItem("Marzo");
		comboBox_mesHistorial.addItem("Abril");
		comboBox_mesHistorial.addItem("Mayo");
		comboBox_mesHistorial.addItem("Junio");
		comboBox_mesHistorial.addItem("Julio");
		comboBox_mesHistorial.addItem("Agosto");
		comboBox_mesHistorial.addItem("Septiembre");
		comboBox_mesHistorial.addItem("Octubre");
		comboBox_mesHistorial.addItem("Noviembre");
		comboBox_mesHistorial.addItem("Diciembre");
		comboBox_mesHistorial.setSelectedIndex(-1);
		comboBox_mesHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int today=comboBox_diaHistorial.getSelectedIndex()+1;
				if(comboBox_anioHistorial.getSelectedItem()!=null) {
					comboBox_diaHistorial.removeAllItems();
					addDays(comboBox_diaHistorial,""+comboBox_mesHistorial.getSelectedItem(),""+comboBox_anioHistorial.getSelectedItem());
					if(today>comboBox_diaHistorial.getItemCount())
						comboBox_diaHistorial.setSelectedIndex(comboBox_diaHistorial.getItemCount()-1);
					else 
						comboBox_diaHistorial.setSelectedIndex(today-1);
				}
			}
		});
		
		comboBox_anioHistorial = new JComboBox<String>();
		comboBox_anioHistorial.setBounds(630, 218, 64, 20);
		panel_1.add(comboBox_anioHistorial);
		addYears(comboBox_anioHistorial,Logica.obtenerAnio(),0);
		comboBox_anioHistorial.setSelectedIndex(-1);
		comboBox_anioHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int today=comboBox_diaHistorial.getSelectedIndex()+1;
				
				if(comboBox_mesHistorial.getSelectedItem()!=null) {
					comboBox_diaHistorial.removeAllItems();
					addDays(comboBox_diaHistorial,""+comboBox_mesHistorial.getSelectedItem(),""+comboBox_anioHistorial.getSelectedItem());
					if(today>comboBox_diaHistorial.getItemCount())
						comboBox_diaHistorial.setSelectedIndex(comboBox_diaHistorial.getItemCount()-1);
					else 
						comboBox_diaHistorial.setSelectedIndex(today-1);
				}
			}
		});
		
		lblTipo_1 = new JLabel("Tipo");
		lblTipo_1.setBounds(491, 65, 197, 23);
		lblTipo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo_1.setForeground(Color.WHITE);
		lblTipo_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_1.add(lblTipo_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(465, 52, 271, 2);
		panel_1.add(separator_2);
		
		lblFecha_1 = new JLabel("Fecha");
		lblFecha_1.setBounds(491, 193, 197, 14);
		lblFecha_1.setForeground(Color.WHITE);
		lblFecha_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblFecha_1);
		
		textField_montoInf = new JTextField();
		textField_montoInf.setBounds(625, 304, 86, 20);
		panel_1.add(textField_montoInf);
		textField_montoInf.setColumns(10);
		
		textField_montoSup = new JTextField();
		textField_montoSup.setBounds(625, 335, 86, 20);
		panel_1.add(textField_montoSup);
		textField_montoSup.setColumns(10);
		
		lblMontoMaximo = new JLabel("Monto Maximo");
		lblMontoMaximo.setBounds(491, 334, 123, 19);
		lblMontoMaximo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMontoMaximo.setForeground(Color.WHITE);
		lblMontoMaximo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblMontoMaximo);
		
		lblMontoMinimo = new JLabel("Monto Minimo");
		lblMontoMinimo.setBounds(491, 300, 123, 20);
		lblMontoMinimo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMontoMinimo.setForeground(Color.WHITE);
		lblMontoMinimo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblMontoMinimo);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(465, 180, 271, 2);
		panel_1.add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(465, 278, 271, 2);
		panel_1.add(separator_4);
		
		btn_filtro = new JPanel();
		
		btn_filtro.setBackground(color_btn_lateral);
		btn_filtro.setLayout(null);
		btn_filtro.setBounds(550,370,100,20);
		panel_1.add(btn_filtro);
		
		JLabel lblAplicarFiltro = new JLabel("Aplicar Filtros");
		lblAplicarFiltro.setBounds(0, 0, 100, 18);
		lblAplicarFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAplicarFiltro.setForeground(Color.WHITE);
		lblAplicarFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		btn_filtro.add(lblAplicarFiltro);
		btn_filtro.addMouseListener(new MouseListener(){

			
			public void mouseClicked(MouseEvent arg0) {	
			}
			public void mouseEntered(MouseEvent arg0) {
				btn_filtro.setBackground(color_btn_lateral_selected);
			}
			public void mouseExited(MouseEvent arg0) {	
				btn_filtro.setBackground(color_btn_lateral);	
			}	
			public void mousePressed(MouseEvent arg0) {
				
				int t1=0,t2=0,t3=0;
				if(rdbtn_IngresoHistorial.isSelected())
					t1=1;
				if(rdbtn_GastoHistorial.isSelected())
					t2=2;
				if(rdbtn_PrestamoHistorial.isSelected())
					t3=3;
				int montoMin=Integer.MIN_VALUE;
				int montoMax=Integer.MAX_VALUE;
				if(!textField_montoInf.getText().trim().equals(""))
					montoMin=Integer.parseInt(textField_montoInf.getText());
				if(!textField_montoSup.getText().trim().equals(""))
					montoMax=Integer.parseInt(textField_montoSup.getText());
				//String fecha=logica.obtenerDiayNombre()+"-"+logica.obtenerMes()+"-"+logica.obtenerAnio();
				String fecha="0-0-0";
				if(comboBox_diaHistorial.getSelectedIndex()!=-1 && comboBox_mesHistorial.getSelectedIndex()!=-1 && comboBox_anioHistorial.getSelectedIndex()!=-1 )
					fecha=(comboBox_diaHistorial.getSelectedIndex()+1)+"-"+(comboBox_mesHistorial.getSelectedIndex()+1)+"-"+comboBox_anioHistorial.getSelectedItem();
				actualizarTablaHistorial(mostrarTipos(t1,t2,t3,montoMin,montoMax,fecha));	
			}
			public void mouseReleased(MouseEvent arg0) {	
			}	
		});

		JPanel btnCalcularSaldo = new JPanel();
		btnCalcularSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCalcularSaldo.setBounds(308, 19, 123, 23);
		btnCalcularSaldo.setForeground(Color.WHITE);	
		btnCalcularSaldo.setBackground(color_btn_lateral);	
		panel_1.add(btnCalcularSaldo);
		btnCalcularSaldo.setLayout(null);
		btnCalcularSaldo.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {	
			}
			public void mouseEntered(MouseEvent arg0) {
				btnCalcularSaldo.setBackground(color_btn_lateral_selected);
			}
			public void mouseExited(MouseEvent arg0) {	
				btnCalcularSaldo.setBackground(color_btn_lateral);	
			}	
			public void mousePressed(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "El saldo prestado es: "+logica.calcularSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
			}
			public void mouseReleased(MouseEvent arg0) {	
			}	
		});
		JLabel lblCalcularSaldo = new JLabel("Calcular Saldo");
		lblCalcularSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalcularSaldo.setForeground(Color.WHITE);
		lblCalcularSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCalcularSaldo.setBounds(0, 0, 123, 23);
		btnCalcularSaldo.add(lblCalcularSaldo);
		
		
		panel_config = new JPanel();
		panel_config.setBounds(0, 0, 794, 472);
		frame.getContentPane().add(panel_config);
		panel_config.setLayout(null);
		panel_config.setVisible(false);
		
		panel_3 = new JPanel();
		panel_3.setBackground(color_header);
		panel_3.setBounds(42, 28, 748, 404);
		panel_config.add(panel_3);
		panel_3.setLayout(null);
		
		lblConfiguracin = new JLabel("Configuraci\u00F3n");
		lblConfiguracin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblConfiguracin.setForeground(Color.WHITE);
		lblConfiguracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracin.setBounds(10, 11, 716, 30);
		panel_3.add(lblConfiguracin);
		
		btn_guardar = new JPanel();
		btn_guardar.setBounds(118, 369, 154, 25);
		btn_guardar.setBackground(color_btn_lateral);
		panel_3.add(btn_guardar);
		btn_guardar.setLayout(null);
		btn_guardar.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
				if(btn_guardar.isEnabled())
				btn_guardar.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent e) {
				if(btn_guardar.isEnabled())
				btn_guardar.setBackground(color_btn_lateral);
			}
			
			public void mousePressed(MouseEvent e) {
				if(btn_guardar.isEnabled()) {
					esquema=comboBox_3.getSelectedItem().toString();
					frame.setVisible(false);
					cambiarEsquemaColor(esquema);
					logica.guardarEsquema(esquema);
					reset();
					frame.setVisible(true);
					if(esquema.equals("Luquitas")) {
						comboBox_3.setEnabled(false);
						JOptionPane.showMessageDialog(null, "AHHH YA NO AI VUELTA ATRAS BOLUDIN", "ALTO MALARDO", JOptionPane.INFORMATION_MESSAGE);
						btn_guardar.setEnabled(false);
					}
				}
			}
			
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		lblGuardarCambios = new JLabel("Guardar Cambios");
		lblGuardarCambios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGuardarCambios.setForeground(Color.WHITE);
		lblGuardarCambios.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardarCambios.setBounds(0, 0, 154, 25);
		btn_guardar.add(lblGuardarCambios);
		
		
		btn_reestablecer = new JPanel();
		btn_reestablecer.setBounds(452, 369, 154, 25);
		panel_3.add(btn_reestablecer);
		btn_reestablecer.setBackground(color_btn_lateral);
		btn_reestablecer.setLayout(null);
		btn_reestablecer.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				btn_reestablecer.setBackground(color_btn_lateral_selected);
			}

			
			public void mouseExited(MouseEvent e) {
				btn_reestablecer.setBackground(color_btn_lateral);
			}

			
			public void mousePressed(MouseEvent e) {
				if(!esquema.equals("Luquitas")) {
					cambiarEsquemaColor("");
					logica.guardarEsquema(esquema);
				}
				else {
					comboBox_3.setEnabled(false);
					JOptionPane.showMessageDialog(null, "CAISTE SIDIN, ALTO MALARDO SOS", "COSCUARMY", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			
			public void mouseReleased(MouseEvent e) {
			}
			
		});

		lblReestablecerPredet = new JLabel("Reestablecer predeterminado");
		lblReestablecerPredet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblReestablecerPredet.setForeground(Color.WHITE);
		lblReestablecerPredet.setHorizontalAlignment(SwingConstants.CENTER);
		lblReestablecerPredet.setBounds(0, 0, 154, 25);
		btn_reestablecer.add(lblReestablecerPredet);
		
		lblEsquemaDeColor = new JLabel("Esquema de Color");
		lblEsquemaDeColor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEsquemaDeColor.setForeground(Color.WHITE);
		lblEsquemaDeColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsquemaDeColor.setBounds(118, 121, 154, 14);
		panel_3.add(lblEsquemaDeColor);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(452, 118, 154, 20);
		panel_3.add(comboBox_3);
		if(esquema.equals("Luquitas"))
			comboBox_3.setEnabled(false);
		comboBox_3.addItem("Fullgray");
		comboBox_3.addItem("Sunset");
		comboBox_3.addItem("Scarlet");
		comboBox_3.addItem("Luquitas");
		comboBox_3.setSelectedItem(esquema);
		
		
		btn_eliminar = new JPanel();
		btn_eliminar.setBounds(272, 273, 179, 25);
		btn_eliminar.setBackground(color_btn_lateral);
		panel_3.add(btn_eliminar);
		btn_eliminar.setLayout(null);
		btn_eliminar.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				btn_eliminar.setBackground(color_btn_lateral_selected);
			}

			public void mouseExited(MouseEvent arg0) {
				btn_eliminar.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				int j=JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar los datos?","Confirmación",JOptionPane.OK_CANCEL_OPTION);
				if(j==0) {
					logica.limpiarHistorialAux();
					lbl_saldoActual.setText("0.00");
					JOptionPane.showMessageDialog(null, "Se ha eliminado el historial", "Aviso", JOptionPane.PLAIN_MESSAGE);
					actualizarTablaDiez(actualizarTabla());
					actualizarTablaHistorial(actualizarTabla());
					logica.saveAux();
				}
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		
		lblEliminarDatos = new JLabel("Eliminar Datos");
		lblEliminarDatos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEliminarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarDatos.setForeground(Color.WHITE);
		lblEliminarDatos.setBounds(0, 0, 179, 25);
		btn_eliminar.add(lblEliminarDatos);
		
		/**============CODIGO DE CREACION DEL PANEL ACERCA DE============*/
		
		panel_acercaDe = new JPanel();
		panel_acercaDe.setBounds(0, 0, 800, 472);
		frame.getContentPane().add(panel_acercaDe);
		panel_acercaDe.setLayout(null);
		panel_acercaDe.setVisible(false);
		
		
		panel_2 = new JPanel();
		panel_2.setBackground(color_header);
		panel_2.setBounds(42, 28, 748, 404);
		panel_acercaDe.add(panel_2);
		panel_2.setLayout(null);
		
		lblAcercaDe_1 = new JLabel("Acerca De");
		lblAcercaDe_1.setForeground(Color.WHITE);
		lblAcercaDe_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAcercaDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe_1.setBounds(0, 11, 748, 30);
		panel_2.add(lblAcercaDe_1);
		
		lblAbout1 = new JLabel("Aplicacion creada por Juan Manuel Puhl");
		lblAbout1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout1.setForeground(Color.WHITE);
		lblAbout1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout1.setBounds(226, 66, 307, 30);
		panel_2.add(lblAbout1);
		
		lblAbout2 = new JLabel("Version 2.0");
		lblAbout2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout2.setForeground(Color.WHITE);
		lblAbout2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout2.setBounds(226, 193, 307, 14);
		panel_2.add(lblAbout2);
		
		lblAbout3 = new JLabel("Diciembre 2018");
		lblAbout3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout3.setForeground(Color.WHITE);
		lblAbout3.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout3.setBounds(226, 218, 307, 14);
		panel_2.add(lblAbout3);
		
		lblAbout4 = new JLabel("Software Libre");
		lblAbout4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout4.setForeground(Color.WHITE);
		lblAbout4.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout4.setBounds(226, 243, 307, 14);
		panel_2.add(lblAbout4);
		
		lblAbout5 = new JLabel("DoubleG");
		lblAbout5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout5.setForeground(Color.WHITE);
		lblAbout5.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout5.setBounds(226, 168, 307, 14);
		panel_2.add(lblAbout5);
		
		lblAbout6 = new JLabel("e-mail: juanmanuelpuhl@hotmail.com.ar");
		lblAbout6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAbout6.setForeground(Color.WHITE);
		lblAbout6.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout6.setBounds(226, 107, 307, 14);
		panel_2.add(lblAbout6);
		
		
		/**============CODIGO DE CREACION DEL PANEL HISTORIAL============*/
		actualizarTablaHistorial(actualizarTabla());
		
		
		/**============CODIGO DE CREACION DEL PANEL INSERCION============*/
		
		panel_insercion = new JPanel();
		panel_insercion.setBounds(0, 0, 800, 472);
		frame.getContentPane().add(panel_insercion);
		panel_insercion.setLayout(null);
		panel_insercion.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				menu_lateral.setBounds(0,0,40,472);
				enableComponents(panel_general,true);
				enableComponents(panel_insercion,true);
				selected=false;
			}

			public void mouseEntered(MouseEvent arg0) {	
			}

			public void mouseExited(MouseEvent arg0) {	
			}

			public void mousePressed(MouseEvent arg0) {	
			}

			public void mouseReleased(MouseEvent arg0) {	
			}
			
		});
		
		panel = new JPanel();
		panel.setBackground(color_header);
		panel.setBounds(42, 28, 748, 404);
		panel_insercion.add(panel);
		panel.setLayout(null);
		
		textField_monto = new JTextField();
		textField_monto.setBounds(287, 92, 241, 20);
		panel.add(textField_monto);
		textField_monto.setColumns(10);
		
		textField_desc = new JTextField();
		textField_desc.setBounds(287, 123, 241, 20);
		panel.add(textField_desc);
		textField_desc.setColumns(10);
		
		radiobtn_Ingreso = new JRadioButton("Ingreso");
		radiobtn_Ingreso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		radiobtn_Ingreso.setForeground(Color.WHITE);
		radiobtn_Ingreso.setOpaque(false);
		radiobtn_Ingreso.setBounds(169, 236, 109, 23);
		radiobtn_Ingreso.setSelected(true);
		panel.add(radiobtn_Ingreso);
		
		radiobtn_Gasto = new JRadioButton("Gasto");
		radiobtn_Gasto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		radiobtn_Gasto.setForeground(Color.WHITE);
		radiobtn_Gasto.setOpaque(false);
		radiobtn_Gasto.setBounds(169, 262, 109, 23);
		panel.add(radiobtn_Gasto);
		
		radiobtn_Prestamo = new JRadioButton("Prestamo");
		radiobtn_Prestamo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		radiobtn_Prestamo.setForeground(Color.WHITE);
		radiobtn_Prestamo.setOpaque(false);
		radiobtn_Prestamo.setBounds(169, 288, 109, 23);
		panel.add(radiobtn_Prestamo);
		
		ButtonGroup grupo= new ButtonGroup();
		grupo.add(radiobtn_Ingreso);
		grupo.add(radiobtn_Gasto);
		grupo.add(radiobtn_Prestamo);
		
		comboBox_dia = new JComboBox<String>();
		comboBox_dia.setBounds(400, 239, 52, 20);
		addDays(comboBox_dia,Logica.obtenerMes(),Logica.obtenerAnio());
		comboBox_dia.setSelectedIndex(Integer.parseInt(Logica.obtenerDiayNombre())-1);
		panel.add(comboBox_dia);
		
		comboBox_mes = new JComboBox<String>();
		comboBox_mes.setBounds(462, 239, 87, 20);
		comboBox_mes.addItem("Enero");
		comboBox_mes.addItem("Febrero");
		comboBox_mes.addItem("Marzo");
		comboBox_mes.addItem("Abril");
		comboBox_mes.addItem("Mayo");
		comboBox_mes.addItem("Junio");
		comboBox_mes.addItem("Julio");
		comboBox_mes.addItem("Agosto");
		comboBox_mes.addItem("Septiembre");
		comboBox_mes.addItem("Octubre");
		comboBox_mes.addItem("Noviembre");
		comboBox_mes.addItem("Diciembre");
		panel.add(comboBox_mes);
		String mesActual=Logica.obtenerMes();
		comboBox_mes.setSelectedItem(mesActual);
		comboBox_mes.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				int today=comboBox_dia.getSelectedIndex()+1;
				comboBox_dia.removeAllItems();
				addDays(comboBox_dia,""+comboBox_mes.getSelectedItem(),""+comboBox_anio.getSelectedItem());
				if(today>comboBox_dia.getItemCount()) 
					comboBox_dia.setSelectedIndex(comboBox_dia.getItemCount()-1);
				else 
					comboBox_dia.setSelectedIndex(today-1);
			}
		});
		
		
		comboBox_anio = new JComboBox<String>();
		comboBox_anio.setBounds(559, 239, 52, 20);
		this.addYears(comboBox_anio,Logica.obtenerAnio(), 0);
		comboBox_anio.setSelectedIndex(10);
		panel.add(comboBox_anio);
		comboBox_anio.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				int today=comboBox_dia.getSelectedIndex()+1;
				comboBox_dia.removeAllItems();
				String mes=""+comboBox_mes.getSelectedItem();
				addDays(comboBox_dia,mes,""+comboBox_anio.getSelectedItem());
				if(today>comboBox_dia.getItemCount()) 
					comboBox_dia.setSelectedIndex(comboBox_dia.getItemCount()-1);
				else 
					comboBox_dia.setSelectedIndex(today-1);
			}
		});
		
		
		lblMonto = new JLabel("Monto");
		lblMonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonto.setForeground(Color.WHITE);
		lblMonto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMonto.setBounds(169, 96, 81, 14);
		panel.add(lblMonto);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setBounds(169, 121, 81, 20);
		panel.add(lblDescripcion);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTipo.setBounds(169, 198, 90, 31);
		panel.add(lblTipo);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(400, 206, 211, 14);
		panel.add(lblFecha);
		
		btn_aceptar = new JPanel();
		btn_aceptar.setBounds(123, 368, 155, 24);
		panel.add(btn_aceptar);
		btn_aceptar.setLayout(null);
		btn_aceptar.setBackground(color_btn_lateral);
		btn_aceptar.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
				if(btn_aceptar.isEnabled())
				btn_aceptar.setBackground(color_btn_lateral_selected);
			}
			
			public void mouseExited(MouseEvent arg0) {
				if(btn_aceptar.isEnabled())
				btn_aceptar.setBackground(color_btn_lateral);
			}

			public void mousePressed(MouseEvent arg0) {
				try {	
					double monto;
					if(!textField_monto.getText().trim().equals("") && !textField_desc.getText().trim().equals("")) {
						monto=Double.parseDouble(textField_monto.getText());
						String desc=textField_desc.getText();
						String dia=comboBox_dia.getSelectedItem().toString();
						int mes=comboBox_mes.getSelectedIndex()+1;
						String anio=comboBox_anio.getSelectedItem().toString();
						int tipo=0;
						if(radiobtn_Ingreso.isSelected())
							tipo=1;
						if(radiobtn_Gasto.isSelected()) 
							tipo=2;
						if(radiobtn_Prestamo.isSelected())
							tipo=3;
						if((tipo==1 || tipo==2) && monto<0) {
							JOptionPane.showMessageDialog(null, "No se debe ingresar un valor negativo", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
						else {
							logica.insertar(monto, desc, tipo, dia+"-"+mes+"-"+anio);
							lbl_saldoActual.setText("$"+logica.saldoAux());
							actualizarTablaDiez(actualizarTabla());
							actualizarTablaHistorial(actualizarTabla());
							JOptionPane.showMessageDialog(null, "Se insertó con exito.", "Aviso", JOptionPane.DEFAULT_OPTION);
							limpiarInsercion();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "No se ingreso un monto y/o una descripcion.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "El monto ingresado no es correcto.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			public void mouseReleased(MouseEvent arg0) { 
			}
		});
		
		lblAceptar = new JLabel("Aceptar");
		lblAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAceptar.setForeground(Color.WHITE);
		lblAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAceptar.setVerticalAlignment(SwingConstants.CENTER);
		lblAceptar.setBounds(0, 0, 155, 25);
		btn_aceptar.add(lblAceptar);
		
		btn_cancelar = new JPanel();
		btn_cancelar.setBounds(456, 368, 155, 24);
		panel.add(btn_cancelar);
		btn_cancelar.setLayout(null);
		btn_cancelar.setBackground(color_btn_lateral);
		btn_cancelar.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				btn_cancelar.setBackground(color_btn_lateral_selected);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				btn_cancelar.setBackground(color_btn_lateral);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				limpiarInsercion();
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setBounds(0, 0, 155, 24);
		lblCancelar.setForeground(Color.WHITE);
		btn_cancelar.add(lblCancelar);
		
		lblNuevaInsercion = new JLabel("Nueva Inserción");
		lblNuevaInsercion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaInsercion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNuevaInsercion.setForeground(Color.WHITE);
		lblNuevaInsercion.setBounds(10, 11, 727, 34);
		panel.add(lblNuevaInsercion);
		
		separator = new JSeparator();
		separator.setBounds(10, 56, 727, 2);
		panel.add(separator);
		panel_insercion.setVisible(false);
		
		
		/**============CODIGO DE CREACION DEL PANEL GENERAL============*/
		
		panel_general = new JPanel();
		panel_general.setBounds(0, 0, 800, 472);
		frame.getContentPane().add(panel_general);
		panel_general.setLayout(null);
		panel_general.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				menu_lateral.setBounds(0,0,40,472);
				enableComponents(panel_general,true);
				enableComponents(panel_insercion,true);
				selected=false;
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		
		
		
		/**============CODIGO DE CREACION DEL PANEL SALDO============*/
	
		panel_saldo = new JPanel();
		panel_saldo.setBackground(color_header);
		panel_saldo.setBounds(39, 11, 755, 175);
		panel_general.add(panel_saldo);
		panel_saldo.setLayout(null);
		
		lblSaldo = new JLabel("Saldo");
		lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldo.setBounds(0, 22, 741, 34);
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		panel_saldo.add(lblSaldo);
		
		lbl_saldoActual = new JLabel("$00,00");
		lbl_saldoActual.setForeground(Color.WHITE);
		lbl_saldoActual.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_saldoActual.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_saldoActual.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lbl_saldoActual.setBounds(0, 56, 741, 89);
		panel_saldo.add(lbl_saldoActual);
		lbl_saldoActual.setText("$"+logica.saldoAux());
		
		
		/**============CODIGO DE CREACION DE LA TABLA ULTIMOS DIEZ============*/
		
		/**Se crea la tabla*/
	
		table_ultimasDiez = new JTable();
		Object [][] arr=actualizarTabla();
		actualizarTablaDiez(arr);
		
		/**============CODIGO DE CREACION DEL SCROLLPANE PARA LA TABLA============*/
		
		scrollPane = new JScrollPane(table_ultimasDiez);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(56, 246, 722, 180);
		panel_general.add(scrollPane);
		
		/**============CODIGO DE CREACION DE LA ETIQUETA ULTIMOS DIEZ============*/
		
		lblUltimasDiez = new JLabel("Ultimos Diez Movimientos");
		lblUltimasDiez.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblUltimasDiez.setHorizontalAlignment(SwingConstants.CENTER);
		lblUltimasDiez.setBounds(56, 214, 722, 21);
		panel_general.add(lblUltimasDiez);
	}
	
	private void addYears(JComboBox<String>c ,String year,int i) {
		if(i<=10) {
			int aux=Integer.parseInt(year);
			--aux;
			addYears(c,""+aux,++i);
			c.addItem(year);
		}
	}
	private void addDays(JComboBox<String> c,String month, String year) {
		int cantDias=obtenerDias(month,year);
		for(int i=1; i<=cantDias; i++) {
			c.addItem(""+i);
		}
	}
	
	private int obtenerDias(String month, String year) {
		int y=Integer.parseInt(year);
		if(month.equals("Enero") || month.equals("Marzo") || month.equals("Mayo") || month.equals("Julio") || month.equals("Agosto") || month.equals("Octubre") || month.equals("Diciembre")) {
			return 31;
		}
		else
		if(month.equals("Abril") || month.equals("Junio") || month.equals("Septiembre") || month.equals("Noviembre")) {
			return 30;
		}
		else
		if(month.equals("Febrero") && ((y%4==0 && y%100!=0)|| y%400==0)) {
			return 29;
		}
		else
			return 28;
	}
	
	private void ocultarPaneles() {
		panel_general.setVisible(false);
		panel_insercion.setVisible(false);
		panel_historial.setVisible(false);
		panel_config.setVisible(false);
		panel_acercaDe.setVisible(false);
	}
	
	private void cambiarEsquemaColor(String esq) {
		if(esq.trim().equals("")) {
			esquema=esq="Scarlet";
		}
		if(esq.equals("Fullgray")) {
			color_btn_lateral=Color.GRAY;
			color_btn_lateral_selected=Color.LIGHT_GRAY;
			color_header=Color.DARK_GRAY;
			color_table_header=Color.DARK_GRAY;
			color_table_selected=Color.LIGHT_GRAY;
		}
		if(esq.equals("Sunset")) {
			color_btn_lateral=Color.decode("#7045af");
			color_header=Color.decode("#182952");
			color_table_header=Color.decode("#182952");
			color_btn_lateral_selected=Color.decode("#2b3595");
			color_table_selected=Color.decode("#2b3595");
		}
		if(esq.equals("Scarlet")) {
			color_btn_lateral=Color.decode("#ff5959");
			color_header=Color.decode("#60424c");
			color_table_header=Color.decode("#984a59");
			color_btn_lateral_selected=Color.decode("#ff8f56");
			color_table_selected=Color.decode("#ff8f56");
		}
		if(esq.equals("Luquitas")) {
			color_btn_lateral=Color.CYAN;
			color_header=Color.CYAN;
			color_table_header=Color.CYAN;
			color_btn_lateral_selected=Color.CYAN;
			color_table_selected=Color.CYAN;
		}
		
		
	}
	
	private Object[][] mostrarTipos(int t1,int t2,int t3,int montoMin, int montoMax,String fecha){
		Object[][] arr=new Object[0][4];
		arr=logica.filtrar(arr, 0, 4, t1, t2, t3,montoMin,montoMax,fecha);
		return arr;
	}
	
	private Object[][] actualizarTabla() {
		Object[][] arr=new Object[0][4];
		arr=logica.mostrarHistorial(arr,0,4);
		return arr;
	}
	private void actualizarTablaDiez(Object[][] arr) {
		String[] columnNames= {"Fecha","Tipo","Monto","Descripcion"};
		DefaultTableModel modelo= new DefaultTableModel(new Object[0][4],columnNames);
		Object[] toAdd= new Object[4];
		int cant=0,i=arr.length-1;
		while(cant<MAXFILAS && i>=0) {
			for(int j=0; j<MAXCOLUMNAS; j++) {
				toAdd[j]=arr[i][j];
			}
			if(toAdd[0]!=null) {
				modelo.addRow(toAdd);
				cant++;
			}
			i--;
		}
		table_ultimasDiez.setMaximumSize(new Dimension(10, 4));
		table_ultimasDiez.setModel(modelo);
		personalizarTablaDiez();
	}
	private void actualizarTablaHistorial(Object[][] arr) {
		String[] columnNames= {"Fecha","Tipo","Monto","Descripcion"};
		DefaultTableModel modelo= new DefaultTableModel(new Object[0][4],columnNames);
		Object[] toAdd= new Object[4];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<4; j++) {
				toAdd[j]=arr[i][j];
			}
			if(toAdd[0]!=null)
				modelo.addRow(toAdd);
			else
				break;
		}
		table_historial.setMaximumSize(new Dimension(10, 4));
		table_historial.setModel(modelo);
		personalizarTablaHistorial();
	}
	private void personalizarTablaDiez() {
				
		table_ultimasDiez.setGridColor(color_btn_lateral_selected);
		table_ultimasDiez.setSelectionBackground(color_table_selected);
		table_ultimasDiez.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_ultimasDiez.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table_ultimasDiez.setDefaultEditor(Object.class,null); //No puede editarse el contenido de la celda

		/**Cambio el color de los headers de las columnas y el color de las letras*/
		DefaultTableCellRenderer headerRenderer=new DefaultTableCellRenderer();
		headerRenderer.setBackground(color_table_header);
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	
		table_ultimasDiez.getTableHeader().setResizingAllowed(false);//No se puede modificar el tamaño de las columnas
		
		/**Seteo el tamaño de las columnas*/
		table_ultimasDiez.getColumnModel().getColumn(0).setPreferredWidth(80);
		table_ultimasDiez.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_ultimasDiez.getColumnModel().getColumn(2).setPreferredWidth(80);
		table_ultimasDiez.getColumnModel().getColumn(3).setPreferredWidth(480);
		
		/**Seteo que el texto este centrado*/
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0; i<MAXCOLUMNAS; i++) {
			table_ultimasDiez.getColumnModel().getColumn(i).setCellRenderer(tcr);
			table_ultimasDiez.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	}
	
	private void personalizarTablaHistorial() {
		table_historial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_historial.setGridColor(color_btn_lateral_selected);
		table_historial.setSelectionBackground(color_table_selected);
		table_historial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_historial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table_historial.setDefaultEditor(Object.class,null); //No puede editarse el contenido de la celda
		
		/**Cambio el color de los headers de las columnas y el color de las letras*/
		DefaultTableCellRenderer headerRendererHistorial=new DefaultTableCellRenderer();
		headerRendererHistorial.setBackground(color_table_header);
		headerRendererHistorial.setForeground(Color.WHITE);
		headerRendererHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		
		table_historial.getTableHeader().setResizingAllowed(false);//No se puede modificar el tamaño de las columnas
		
		/**Seteo el tamaño de las columnas*/
		table_historial.getColumnModel().getColumn(0).setPreferredWidth(80);
		table_historial.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_historial.getColumnModel().getColumn(2).setPreferredWidth(80);
		table_historial.getColumnModel().getColumn(3).setPreferredWidth(169);
		
		/**Seteo que el texto este centrado*/
		DefaultTableCellRenderer tcrHistorial = new DefaultTableCellRenderer();
		tcrHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0; i<MAXCOLUMNAS; i++) {
			table_historial.getColumnModel().getColumn(i).setCellRenderer(tcrHistorial);
			table_historial.getColumnModel().getColumn(i).setHeaderRenderer(headerRendererHistorial);
		}
	}
	private void limpiarInsercion() {
		comboBox_dia.setSelectedItem(Logica.obtenerDiayNombre());
		comboBox_mes.setSelectedItem(Logica.obtenerMes());
		comboBox_anio.setSelectedItem(Logica.obtenerAnio());
		textField_desc.setText("");
		textField_monto.setText("");
		radiobtn_Ingreso.setSelected(true);
	}
	
	private void limpiarHistorial() {
		actualizarTablaHistorial(actualizarTabla());
		comboBox_diaHistorial.setSelectedIndex(-1);
		comboBox_mesHistorial.setSelectedIndex(-1);
		comboBox_anioHistorial.setSelectedIndex(-1);
		textField_montoInf.setText("");
		textField_montoSup.setText("");
		rdbtn_IngresoHistorial.setSelected(true);
		rdbtn_GastoHistorial.setSelected(true);
		rdbtn_PrestamoHistorial.setSelected(true);
	}
	private void reset() {
		menu_lateral.setBackground(color_btn_lateral);
		btn_menu.setBackground(color_btn_lateral);
		btn_home.setBackground(color_btn_lateral);
		btn_add.setBackground(color_btn_lateral);
		btn_historial.setBackground(color_btn_lateral);
		btn_config.setBackground(color_btn_lateral);
		btn_about.setBackground(color_btn_lateral);
		panel_3.setBackground(color_header);
		btn_guardar.setBackground(color_btn_lateral);
		btn_reestablecer.setBackground(color_btn_lateral);
		btn_eliminar.setBackground(color_btn_lateral);
		panel_2.setBackground(color_header);
		panel_1.setBackground(color_header);
		btn_filtro.setBackground(color_btn_lateral);
		panel.setBackground(color_header);
		btn_aceptar.setBackground(color_btn_lateral);
		btn_cancelar.setBackground(color_btn_lateral);
		panel_saldo.setBackground(color_header);
		personalizarTablaDiez();
		personalizarTablaHistorial();
	}
}
