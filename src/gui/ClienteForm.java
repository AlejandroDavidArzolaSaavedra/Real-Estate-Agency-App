package gui;

import file.AgenciaFile;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteForm extends javax.swing.JFrame {

    public Inmobiliaria inmobiliaria;
    public Cliente cliente;
    public AgenciaFile file;

    public ClienteForm() {
        initComponents();

        //file.loadFromFile(inmobiliaria);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/iconoSALIR.png"));
        Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(salirIIcon.getWidth(), salirIIcon.getHeight(), Image.SCALE_DEFAULT));
        salirIIcon.setIcon(fondo);
        this.repaint();
        realizarBusquedaButton.setEnabled(false);
        contactarButton.setEnabled(false);
        txtFotoInmueble.setEditable(false);
        btnRealizarCambios.setEnabled(false);
        btnPublicarAnuncio.setEnabled(false);
        infoInmuebleList1.setEnabled(false);
        lblFecha.setText("Inicio de Sesion: " + fechaUsuario());

    }

    List<Anuncio> getListAnunciosAlquilados(List<Anuncio> inmuebles) {
        List<Anuncio> anunciosPorPrecio = new ArrayList<>();

        inmuebles.stream().filter((anuncios) -> (anuncios.getTipoOperacion().toString().equals("ALQUILER"))).forEachOrdered((anuncios) -> {
            anunciosPorPrecio.add(anuncios);
        });
        return anunciosPorPrecio;
    }

    List<Anuncio> getListAnunciosVendidos(List<Anuncio> inmuebles) {
        List<Anuncio> anunciosPorPrecio = new ArrayList<>();

        inmuebles.stream().filter((anuncios) -> (anuncios.getTipoOperacion().toString().equals("VENTA"))).forEachOrdered((anuncios) -> {
            anunciosPorPrecio.add(anuncios);
        });
        return anunciosPorPrecio;
    }

    List<Anuncio> getListAnunciosEntrePrecio(int precioDesde, int precioHasta) {
        List<Anuncio> anunciosPorPrecio = new ArrayList<>();

        listaAnunciosTotales().stream().filter((anuncios) -> (anuncios.getPrecio() >= precioDesde
                && anuncios.getPrecio() <= precioHasta)).forEachOrdered((anuncios) -> {
            anunciosPorPrecio.add(anuncios);
        });

        for (Anuncio anuncio : anunciosPorPrecio) {
            System.out.println(anuncio);
        }

        return anunciosPorPrecio;
    }

    List<Anuncio> getListAnunciosPorMunicipio(String municipio) {
        List<Anuncio> anunciosPorMunicipio = new ArrayList<>();

        listaAnunciosTotales().stream().filter((anuncios) -> (anuncios.getInmueble().getDireccion().getMunicipio().equals(municipio))).forEachOrdered((anuncios) -> {
            anunciosPorMunicipio.add(anuncios);
        });
        return anunciosPorMunicipio;
    }

    void showClient(String c) {
        String mensaje = "";
        infoContactoTextArea.removeAll();
        for (Mensaje mensajes : cliente.getFrom()) {
            if (mensajes.getFrom().toString().equals(c)) {
                mensaje += mensajes.getMensaje() + "\n--------------------\n";
            }
        }
        infoContactoTextArea.setText(mensaje);
    }

    ArrayList<Anuncio> listaAnunciosTotales() {
        ArrayList<Anuncio> anunciosTotales = new ArrayList<>();

        for (CatalogoAnuncios catalogo : inmobiliaria.getCatalogo()) {
            if (catalogo.getTipoCatalogo().toString().equals("PUBLICADOS")) {
                for (Anuncio anuncio : catalogo.getAnuncios()) {
                    anunciosTotales.add(anuncio);
                }
            }
        }
        return anunciosTotales;
    }

    void showInmueble(Anuncio i) {
        infoInmuebleList1.removeAll();
        infoInmuebleList1.setText("Nombre: " + i.getTitulo()
                + "\n" + "Descripción: " + i.getDescripcion()
                + "\n" + "Precio: " + i.getPrecio()
                + "\n" + "Tipo: " + i.getInmueble().getTipoInmueble().toString()
                + "\n" + "Superficie: " + i.getInmueble().getSuperficie().toString()
                + "\n" + "Numero habitaciones: " + Integer.toString(i.getInmueble().getNumeroHabitaciones())
                + "\n" + "Número de cuartos de baño: " + i.getInmueble().getNumeroDeWc()
                + "\n" + "Estado inmueble: " + i.getInmueble().getTipoEstadoInmueble().toString()
                + "\n" + "Direccion: " + i.getInmueble().getDireccion().toString());
    }

    void showAnunciosList() {
        int i;
        inmueblesEncontradosList.removeAll();
        //for (i = 0; i < anuncios.getAnunciosList().size(); i++) {}
        // inmueblesEncontradosList.add(anuncios.getAnunciosList().get(i).getInmueble());
    }

    void comprobarPublicarAnuncio() {

        if (txCodigoPostal.getText().equals("") ^ txtNumero.getText().equals("")) {
            btnPublicarAnuncio.setEnabled(false);
        } else {
            if (txCodigoPostal.getText().matches("\\d{5,}") & txtNumero.getText().matches("\\d*")
                    & txtPiso.getText().matches("\\d*") & txtSuperficie.getText().matches("\\d*")
                    & cbNumHabitaciones.getSelectedItem().toString().matches("\\d")
                    & !cbMunicipio.getSelectedItem().toString().matches("\\...")) {
                btnPublicarAnuncio.setEnabled(true);
            } else {
                realizarBusquedaButton.setEnabled(false);
            }

        }

    }

    private String fechaUsuario() {
        Calendar fecha = new GregorianCalendar();
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        inmuebleParaButtonGroup = new javax.swing.ButtonGroup();
        filtrosButtonGroup = new javax.swing.ButtonGroup();
        precioButtonGroup = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jSlider1 = new javax.swing.JSlider();
        jPanel = new javax.swing.JPanel();
        lblAgencia = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        tabEditarPerfil = new javax.swing.JTabbedPane();
        panelBuscarInmueble = new javax.swing.JPanel();
        buscarInmueblePanel = new javax.swing.JPanel();
        municipioRadioButton = new javax.swing.JRadioButton();
        filtrosBusquedaLabel = new javax.swing.JLabel();
        InmueblesEncontradosLabel = new javax.swing.JLabel();
        precioRadioButton = new javax.swing.JRadioButton();
        precioDesdeLabel = new javax.swing.JLabel();
        precioHastaLabel = new javax.swing.JLabel();
        precioHastaTextField = new javax.swing.JTextField();
        municipioLabel = new javax.swing.JLabel();
        precioDesdeTextField = new javax.swing.JTextField();
        compraRadioButton = new javax.swing.JRadioButton();
        alquilerRadioButton = new javax.swing.JRadioButton();
        inmuebleparaLabel = new javax.swing.JLabel();
        lblPreferencias = new javax.swing.JLabel();
        informacionDelInmuebleLabel = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        realizarBusquedaButton = new javax.swing.JButton();
        inmueblesEncontradosList = new java.awt.List();
        jScrollPane3 = new javax.swing.JScrollPane();
        infoInmuebleList1 = new javax.swing.JTextArea();
        municipiosTextField = new javax.swing.JComboBox<>();
        contactarButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        infoBuscarInmuebleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelAñadirAnuncio = new javax.swing.JPanel();
        lblTipoInmueble = new javax.swing.JLabel();
        lblNumeroHabitaciones = new javax.swing.JLabel();
        lblSuperficie = new javax.swing.JLabel();
        cbNumHabitaciones = new javax.swing.JComboBox<>();
        cbtipoInmueble = new javax.swing.JComboBox<>();
        btnAdjuntarImagen = new javax.swing.JButton();
        txtSuperficie = new java.awt.TextField();
        lblFotoInmueble = new javax.swing.JLabel();
        txtFotoInmueble = new javax.swing.JTextField();
        panelAñadir = new javax.swing.JPanel();
        lblMunicipio = new javax.swing.JLabel();
        cbMunicipio = new javax.swing.JComboBox<>();
        lblCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        lblCodigoPostal = new javax.swing.JLabel();
        txCodigoPostal = new javax.swing.JTextField();
        lblNumeroInmueble = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        pIsoLabel = new javax.swing.JLabel();
        txtPiso = new javax.swing.JTextField();
        rbAlquiler = new javax.swing.JRadioButton();
        rbVenta = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lblInfoDireccion = new javax.swing.JLabel();
        lblInfoDelInmu = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btnPublicarAnuncio = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAreaDescripcionAnuncio = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtPrecio = new java.awt.TextField();
        lblTituloAnuncio = new javax.swing.JLabel();
        txtTituloAnuncio = new javax.swing.JTextField();
        cbNumeroBaños = new javax.swing.JComboBox<>();
        lblNumeroBaños = new javax.swing.JLabel();
        cbEstadoInmueble = new javax.swing.JComboBox<>();
        lblEstadoInmueble = new javax.swing.JLabel();
        infoBuscarFotoErorr = new javax.swing.JLabel();
        infoAñadirInmuebleLabel = new javax.swing.JLabel();
        lblErrorAñadirAnuncio = new javax.swing.JLabel();
        panelHistorial = new javax.swing.JPanel();
        historialMensajesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoContactoTextArea = new javax.swing.JTextArea();
        historialMensajesTextArea = new java.awt.List();
        infoHistorialLabel = new javax.swing.JLabel();
        personasInteresadasLabel = new javax.swing.JLabel();
        datosContactoLabel = new javax.swing.JLabel();
        cargarHistorialButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelEditarPerfil = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelmodificar = new javax.swing.JPanel();
        nombreLabel = new javax.swing.JLabel();
        apellidosLabel = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        contraseñaNuevaLabel = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        repetirContraseñaLabel = new javax.swing.JLabel();
        txtRepetirPassword = new javax.swing.JPasswordField();
        lblCorreoElectronico = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtPasswordActual = new javax.swing.JPasswordField();
        infoIngreseContraseñaLabel = new javax.swing.JLabel();
        btnRealizarCambios = new javax.swing.JButton();
        infoEditarPerfilLabel = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        correoSesionLabel = new javax.swing.JLabel();
        salirSesionLabel = new javax.swing.JLabel();
        salirIIcon = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Superficie m2:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Piso :");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel.setMaximumSize(new java.awt.Dimension(963, 575));
        jPanel.setPreferredSize(new java.awt.Dimension(963, 575));

        lblAgencia.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblAgencia.setForeground(new java.awt.Color(53, 121, 56));
        lblAgencia.setText("AGENCIA INMOBILIARIA GC");

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N

        buscarInmueblePanel.setBackground(new java.awt.Color(53, 121, 56));
        buscarInmueblePanel.setForeground(new java.awt.Color(255, 255, 255));

        municipioRadioButton.setBackground(new java.awt.Color(53, 121, 56));
        filtrosButtonGroup.add(municipioRadioButton);
        municipioRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        municipioRadioButton.setText("Municipio");
        municipioRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                municipioRadioButtonActionPerformed(evt);
            }
        });

        filtrosBusquedaLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        filtrosBusquedaLabel.setForeground(new java.awt.Color(255, 255, 255));
        filtrosBusquedaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filtrosBusquedaLabel.setText("Filtros de busqueda");

        InmueblesEncontradosLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        InmueblesEncontradosLabel.setForeground(new java.awt.Color(255, 255, 255));
        InmueblesEncontradosLabel.setText("Anuncios publicados");

        precioRadioButton.setBackground(new java.awt.Color(53, 121, 56));
        filtrosButtonGroup.add(precioRadioButton);
        precioRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        precioRadioButton.setText("Precio");
        precioRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioRadioButtonActionPerformed(evt);
            }
        });

        precioDesdeLabel.setForeground(new java.awt.Color(255, 255, 255));
        precioDesdeLabel.setText("Precio desde :");

        precioHastaLabel.setForeground(new java.awt.Color(255, 255, 255));
        precioHastaLabel.setText("Precio hasta :");

        precioHastaTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precioHastaTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioHastaTextFieldKeyReleased(evt);
            }
        });

        municipioLabel.setForeground(new java.awt.Color(255, 255, 255));
        municipioLabel.setText("Municipios : ");

        precioDesdeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioDesdeTextFieldActionPerformed(evt);
            }
        });
        precioDesdeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precioDesdeTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioDesdeTextFieldKeyReleased(evt);
            }
        });

        compraRadioButton.setBackground(new java.awt.Color(53, 121, 56));
        inmuebleParaButtonGroup.add(compraRadioButton);
        compraRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        compraRadioButton.setText("Venta");
        compraRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraRadioButtonActionPerformed(evt);
            }
        });

        alquilerRadioButton.setBackground(new java.awt.Color(53, 121, 56));
        inmuebleParaButtonGroup.add(alquilerRadioButton);
        alquilerRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        alquilerRadioButton.setText("Alquiler");
        alquilerRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alquilerRadioButtonActionPerformed(evt);
            }
        });

        inmuebleparaLabel.setForeground(new java.awt.Color(255, 255, 255));
        inmuebleparaLabel.setText("Inmueble para :");

        lblPreferencias.setForeground(new java.awt.Color(255, 255, 255));
        lblPreferencias.setText("Preferencias");

        informacionDelInmuebleLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        informacionDelInmuebleLabel.setForeground(new java.awt.Color(255, 255, 255));
        informacionDelInmuebleLabel.setText("Informacion detallada del inmueble");

        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("Mensaje:");

        realizarBusquedaButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        realizarBusquedaButton.setText("Realizar busqueda");
        realizarBusquedaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarBusquedaButtonActionPerformed(evt);
            }
        });

        inmueblesEncontradosList.setForeground(new java.awt.Color(51, 51, 51));
        inmueblesEncontradosList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inmueblesEncontradosListActionPerformed(evt);
            }
        });

        infoInmuebleList1.setColumns(20);
        infoInmuebleList1.setForeground(new java.awt.Color(51, 51, 51));
        infoInmuebleList1.setRows(5);
        infoInmuebleList1.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jScrollPane3.setViewportView(infoInmuebleList1);

        municipiosTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Las Palmas de Gran Canaria", "Telde", "Agaete", "Aguimes", "Artenara", "Arucas", "Firgas", "Galdar", "Ingenio", "La Aldea de San Nicolas", "Mogán", "Moya", "San Bartolomé de Tirajana", "Santa Brigida", "Santa Lucía de Tirajana", "Santa Maria de Guía", "Tejeda", "Teror", "Valleseco", "Valsequillo de Gran Canaria", "Vega San Mateo" }));
        municipiosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                municipiosTextFieldActionPerformed(evt);
            }
        });

        contactarButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        contactarButton.setText("Contactar");
        contactarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactarButtonActionPerformed(evt);
            }
        });

        txtMensaje.setColumns(20);
        txtMensaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtMensaje.setRows(5);
        jScrollPane5.setViewportView(txtMensaje);

        jButton1.setText("Mirar todos los anuncios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("FOTO");

        javax.swing.GroupLayout buscarInmueblePanelLayout = new javax.swing.GroupLayout(buscarInmueblePanel);
        buscarInmueblePanel.setLayout(buscarInmueblePanelLayout);
        buscarInmueblePanelLayout.setHorizontalGroup(
            buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(contactarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(realizarBusquedaButton))
                            .addComponent(jScrollPane5)))
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(municipioRadioButton, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(precioRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(precioDesdeLabel)
                            .addComponent(precioHastaLabel)
                            .addComponent(municipioLabel))
                        .addGap(18, 18, 18)
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(municipiosTextField, 0, 200, Short.MAX_VALUE)
                            .addComponent(precioDesdeTextField)
                            .addComponent(precioHastaTextField)))
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblPreferencias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filtrosBusquedaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(inmuebleparaLabel)
                        .addGap(18, 18, 18)
                        .addComponent(compraRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alquilerRadioButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscarInmueblePanelLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(InmueblesEncontradosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(inmueblesEncontradosList, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(jLabel8)))
                    .addComponent(informacionDelInmuebleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        buscarInmueblePanelLayout.setVerticalGroup(
            buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addComponent(municipioRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioRadioButton)
                                .addGap(27, 27, 27))
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(filtrosBusquedaLabel)
                                    .addComponent(lblPreferencias))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(municipiosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(municipioLabel))
                                .addGap(11, 11, 11)
                                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(precioDesdeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(precioDesdeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(precioHastaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(precioHastaLabel))))
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alquilerRadioButton)
                            .addComponent(compraRadioButton)
                            .addComponent(inmuebleparaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensaje)
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(realizarBusquedaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contactarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(InmueblesEncontradosLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inmueblesEncontradosList, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(buscarInmueblePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel8))
                            .addGroup(buscarInmueblePanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(informacionDelInmuebleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        infoBuscarInmuebleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        infoBuscarInmuebleLabel.setText("Ingrese todos los filtros para realizar busquedas de los inmuebles que ofertamos!!");

        javax.swing.GroupLayout panelBuscarInmuebleLayout = new javax.swing.GroupLayout(panelBuscarInmueble);
        panelBuscarInmueble.setLayout(panelBuscarInmuebleLayout);
        panelBuscarInmuebleLayout.setHorizontalGroup(
            panelBuscarInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarInmuebleLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(infoBuscarInmuebleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarInmuebleLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(buscarInmueblePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        panelBuscarInmuebleLayout.setVerticalGroup(
            panelBuscarInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarInmuebleLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(infoBuscarInmuebleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarInmueblePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabEditarPerfil.addTab("Buscar Inmueble", panelBuscarInmueble);

        panelAñadirAnuncio.setBackground(new java.awt.Color(53, 121, 56));
        panelAñadirAnuncio.setForeground(new java.awt.Color(255, 255, 255));

        lblTipoInmueble.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTipoInmueble.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoInmueble.setText("Tipo de Inmueble : ");

        lblNumeroHabitaciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumeroHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroHabitaciones.setText("Número de habitaciones : ");

        lblSuperficie.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblSuperficie.setForeground(new java.awt.Color(255, 255, 255));
        lblSuperficie.setText("Superficie m2 :");

        cbNumHabitaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbNumHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNumHabitacionesActionPerformed(evt);
            }
        });

        cbtipoInmueble.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Piso", "Casa", "Chalet", "Apartamento" }));
        cbtipoInmueble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtipoInmuebleActionPerformed(evt);
            }
        });

        btnAdjuntarImagen.setText("adjuntar imagen");
        btnAdjuntarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdjuntarImagenActionPerformed(evt);
            }
        });

        txtSuperficie.setForeground(new java.awt.Color(102, 102, 102));
        txtSuperficie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuperficieActionPerformed(evt);
            }
        });
        txtSuperficie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSuperficieKeyReleased(evt);
            }
        });

        lblFotoInmueble.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblFotoInmueble.setForeground(new java.awt.Color(255, 255, 255));
        lblFotoInmueble.setText("Foto del Inmueble : ");

        txtFotoInmueble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFotoInmuebleActionPerformed(evt);
            }
        });

        lblMunicipio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblMunicipio.setText("Municipio:");

        cbMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Las Palmas de Gran Canaria", "Telde", "Agaete", "Aguimes", "Artenara", "Arucas", "Firgas", "Galdar", "Ingenio", "La Aldea de San Nicolas", "Mogán", "Moya", "San Bartolomé de Tirajana", "Santa Brigida", "Santa Lucía de Tirajana", "Santa Maria de Guía", "Tejeda", "Teror", "Valleseco", "Valsequillo de Gran Canaria", "Vega San Mateo" }));
        cbMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMunicipioActionPerformed(evt);
            }
        });

        lblCalle.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCalle.setText("Calle :");

        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });
        txtCalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCalleKeyReleased(evt);
            }
        });

        lblCodigoPostal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodigoPostal.setText("Codigo Postal:");

        txCodigoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCodigoPostalActionPerformed(evt);
            }
        });
        txCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCodigoPostalKeyReleased(evt);
            }
        });

        lblNumeroInmueble.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumeroInmueble.setText("Numero : ");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });

        pIsoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pIsoLabel.setText("Piso :");

        txtPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPisoActionPerformed(evt);
            }
        });
        txtPiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPisoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelAñadirLayout = new javax.swing.GroupLayout(panelAñadir);
        panelAñadir.setLayout(panelAñadirLayout);
        panelAñadirLayout.setHorizontalGroup(
            panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAñadirLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAñadirLayout.createSequentialGroup()
                        .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCalle)
                            .addComponent(lblMunicipio))
                        .addGap(18, 18, 18)
                        .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAñadirLayout.createSequentialGroup()
                        .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCodigoPostal)
                            .addComponent(lblNumeroInmueble)
                            .addComponent(pIsoLabel))
                        .addGap(18, 18, 18)
                        .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txCodigoPostal)
                            .addComponent(txtNumero))))
                .addGap(44, 44, 44))
        );
        panelAñadirLayout.setVerticalGroup(
            panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAñadirLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMunicipio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalle)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoPostal)
                    .addComponent(txCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroInmueble)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pIsoLabel))
                .addGap(32, 32, 32))
        );

        rbAlquiler.setBackground(new java.awt.Color(53, 121, 56));
        precioButtonGroup.add(rbAlquiler);
        rbAlquiler.setForeground(new java.awt.Color(255, 255, 255));
        rbAlquiler.setText("Alquiler");
        rbAlquiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAlquilerMouseClicked(evt);
            }
        });
        rbAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlquilerActionPerformed(evt);
            }
        });
        rbAlquiler.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbAlquilerKeyPressed(evt);
            }
        });

        rbVenta.setBackground(new java.awt.Color(53, 121, 56));
        precioButtonGroup.add(rbVenta);
        rbVenta.setForeground(new java.awt.Color(255, 255, 255));
        rbVenta.setText("Venta");
        rbVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbVentaMouseClicked(evt);
            }
        });
        rbVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVentaActionPerformed(evt);
            }
        });
        rbVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbVentaKeyPressed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione el tipo de operación");

        lblInfoDireccion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfoDireccion.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoDireccion.setText("Informacion de la direccion");

        lblInfoDelInmu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInfoDelInmu.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoDelInmu.setText("Informacion del Inmueble");

        lblPrecio.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio :");

        btnPublicarAnuncio.setBackground(new java.awt.Color(255, 255, 255));
        btnPublicarAnuncio.setForeground(new java.awt.Color(53, 121, 56));
        btnPublicarAnuncio.setText("Publicar anuncio");
        btnPublicarAnuncio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicarAnuncioActionPerformed(evt);
            }
        });

        txtAreaDescripcionAnuncio.setColumns(20);
        txtAreaDescripcionAnuncio.setRows(5);
        jScrollPane6.setViewportView(txtAreaDescripcionAnuncio);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripcion del Anuncio");

        txtPrecio.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(102, 102, 102));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        lblTituloAnuncio.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloAnuncio.setText("TItulo del Anuncio:");

        cbNumeroBaños.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbNumeroBaños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNumeroBañosActionPerformed(evt);
            }
        });

        lblNumeroBaños.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroBaños.setText("Numero de baños:");

        cbEstadoInmueble.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Buen estado", "Pendiente a reformar", "Nueva obra", " " }));
        cbEstadoInmueble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoInmuebleActionPerformed(evt);
            }
        });

        lblEstadoInmueble.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoInmueble.setText("Estado del Inmueble:");

        javax.swing.GroupLayout panelAñadirAnuncioLayout = new javax.swing.GroupLayout(panelAñadirAnuncio);
        panelAñadirAnuncio.setLayout(panelAñadirAnuncioLayout);
        panelAñadirAnuncioLayout.setHorizontalGroup(
            panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTipoInmueble))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSuperficie, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFotoInmueble, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFotoInmueble, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbtipoInmueble, javax.swing.GroupLayout.Alignment.TRAILING, 0, 177, Short.MAX_VALUE)
                            .addComponent(txtSuperficie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(panelAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInfoDelInmu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                .addComponent(lblInfoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)))))
                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbAlquiler)
                                .addGap(18, 18, 18)
                                .addComponent(rbVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPublicarAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addComponent(btnAdjuntarImagen)
                                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbNumHabitaciones, 0, 183, Short.MAX_VALUE)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblNumeroHabitaciones)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblEstadoInmueble)
                                                    .addComponent(lblNumeroBaños))
                                                .addGap(18, 18, 18)))
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbNumeroBaños, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbEstadoInmueble, 0, 183, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addGap(0, 91, Short.MAX_VALUE)
                                .addComponent(lblTituloAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTituloAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(148, 148, 148))))
        );
        panelAñadirAnuncioLayout.setVerticalGroup(
            panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTituloAnuncio)
                            .addComponent(txtTituloAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInfoDireccion)))
                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblInfoDelInmu)
                                .addGap(4, 4, 4)
                                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFotoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdjuntarImagen)
                                    .addComponent(lblFotoInmueble))
                                .addGap(26, 26, 26))
                            .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(104, 104, 104)
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAñadirAnuncioLayout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbNumHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNumeroHabitaciones))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbNumeroBaños, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNumeroBaños))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbEstadoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEstadoInmueble))
                                        .addGap(52, 52, 52)))))
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPublicarAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbVenta)
                            .addComponent(rbAlquiler)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAñadirAnuncioLayout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbtipoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoInmueble))
                        .addGap(4, 4, 4)
                        .addGroup(panelAñadirAnuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSuperficie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSuperficie))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        infoBuscarFotoErorr.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        infoAñadirInmuebleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        infoAñadirInmuebleLabel.setText("Ingrese todos los datos para poder publicar un anuncio ");

        lblErrorAñadirAnuncio.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(infoAñadirInmuebleLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(panelAñadirAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErrorAñadirAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(infoBuscarFotoErorr, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblErrorAñadirAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(infoAñadirInmuebleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAñadirAnuncio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addComponent(infoBuscarFotoErorr, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabEditarPerfil.addTab("Añadir Anuncio", jPanel1);

        historialMensajesPanel.setBackground(new java.awt.Color(53, 121, 56));
        historialMensajesPanel.setForeground(new java.awt.Color(255, 255, 255));

        infoContactoTextArea.setColumns(20);
        infoContactoTextArea.setRows(5);
        jScrollPane2.setViewportView(infoContactoTextArea);

        javax.swing.GroupLayout historialMensajesPanelLayout = new javax.swing.GroupLayout(historialMensajesPanel);
        historialMensajesPanel.setLayout(historialMensajesPanelLayout);
        historialMensajesPanelLayout.setHorizontalGroup(
            historialMensajesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historialMensajesPanelLayout.createSequentialGroup()
                .addContainerGap(701, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        historialMensajesPanelLayout.setVerticalGroup(
            historialMensajesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historialMensajesPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        historialMensajesTextArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialMensajesTextAreaActionPerformed(evt);
            }
        });

        infoHistorialLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        infoHistorialLabel.setText("Publique algun anuncio para recibir mensajes de otros clientes:");

        personasInteresadasLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        personasInteresadasLabel.setForeground(new java.awt.Color(255, 255, 255));
        personasInteresadasLabel.setText("Personas Interesadas");

        datosContactoLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        datosContactoLabel.setForeground(new java.awt.Color(255, 255, 255));
        datosContactoLabel.setText("Mensaje  del contacto");

        cargarHistorialButton.setBackground(new java.awt.Color(255, 255, 255));
        cargarHistorialButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cargarHistorialButton.setForeground(new java.awt.Color(53, 121, 56));
        cargarHistorialButton.setText("Cargar Historial");
        cargarHistorialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarHistorialButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHistorialLayout = new javax.swing.GroupLayout(panelHistorial);
        panelHistorial.setLayout(panelHistorialLayout);
        panelHistorialLayout.setHorizontalGroup(
            panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHistorialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(panelHistorialLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(personasInteresadasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(datosContactoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
            .addGroup(panelHistorialLayout.createSequentialGroup()
                .addGroup(panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelHistorialLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(historialMensajesTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cargarHistorialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelHistorialLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(infoHistorialLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHistorialLayout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(historialMensajesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)))
        );
        panelHistorialLayout.setVerticalGroup(
            panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistorialLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(infoHistorialLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personasInteresadasLabel)
                    .addComponent(datosContactoLabel))
                .addGroup(panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelHistorialLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(cargarHistorialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelHistorialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historialMensajesTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(panelHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelHistorialLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(historialMensajesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        tabEditarPerfil.addTab("Historial de Mensajes", panelHistorial);

        panelmodificar.setBackground(new java.awt.Color(53, 121, 56));
        panelmodificar.setForeground(new java.awt.Color(255, 255, 255));

        nombreLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        nombreLabel.setForeground(new java.awt.Color(255, 255, 255));
        nombreLabel.setText("Nombre:");

        apellidosLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        apellidosLabel.setForeground(new java.awt.Color(255, 255, 255));
        apellidosLabel.setText("Apellidos:");

        contraseñaNuevaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        contraseñaNuevaLabel.setForeground(new java.awt.Color(255, 255, 255));
        contraseñaNuevaLabel.setText("Contraseña nueva:");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        repetirContraseñaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        repetirContraseñaLabel.setForeground(new java.awt.Color(255, 255, 255));
        repetirContraseñaLabel.setText("Repetir contraseña:");

        txtRepetirPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRepetirPasswordActionPerformed(evt);
            }
        });

        lblCorreoElectronico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreoElectronico.setText("Correo electrónico:");

        txtPasswordActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActualActionPerformed(evt);
            }
        });
        txtPasswordActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordActualKeyReleased(evt);
            }
        });

        infoIngreseContraseñaLabel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        infoIngreseContraseñaLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoIngreseContraseñaLabel.setText("Ingrese tu contraseña para realizar los cambios:");

        btnRealizarCambios.setBackground(new java.awt.Color(255, 255, 255));
        btnRealizarCambios.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnRealizarCambios.setForeground(new java.awt.Color(53, 121, 56));
        btnRealizarCambios.setText("Realizar cambios");
        btnRealizarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCambiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelmodificarLayout = new javax.swing.GroupLayout(panelmodificar);
        panelmodificar.setLayout(panelmodificarLayout);
        panelmodificarLayout.setHorizontalGroup(
            panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelmodificarLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(apellidosLabel)
                    .addComponent(nombreLabel)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(contraseñaNuevaLabel)
                    .addComponent(repetirContraseñaLabel)
                    .addComponent(infoIngreseContraseñaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRealizarCambios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCorreo)
                    .addComponent(txtPassword)
                    .addComponent(txtRepetirPassword)
                    .addComponent(txtPasswordActual, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        panelmodificarLayout.setVerticalGroup(
            panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelmodificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidosLabel)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCorreoElectronico)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contraseñaNuevaLabel)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repetirContraseñaLabel)
                    .addComponent(txtRepetirPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoIngreseContraseñaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRealizarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        infoEditarPerfilLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        infoEditarPerfilLabel.setText("Ingrese los datos que deseas modificar a continuación");

        lblError.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(53, 121, 56));

        javax.swing.GroupLayout panelEditarPerfilLayout = new javax.swing.GroupLayout(panelEditarPerfil);
        panelEditarPerfil.setLayout(panelEditarPerfilLayout);
        panelEditarPerfilLayout.setHorizontalGroup(
            panelEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarPerfilLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEditarPerfilLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(infoEditarPerfilLabel))
                    .addGroup(panelEditarPerfilLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(panelmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        panelEditarPerfilLayout.setVerticalGroup(
            panelEditarPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarPerfilLayout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelEditarPerfilLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(infoEditarPerfilLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabEditarPerfil.addTab("Editar perfil", panelEditarPerfil);

        correoSesionLabel.setBackground(new java.awt.Color(255, 255, 255));
        correoSesionLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        correoSesionLabel.setForeground(new java.awt.Color(102, 204, 255));
        correoSesionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        correoSesionLabel.setText("user@gmail.com");

        salirSesionLabel.setBackground(new java.awt.Color(255, 255, 255));
        salirSesionLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        salirSesionLabel.setForeground(new java.awt.Color(53, 121, 56));
        salirSesionLabel.setText("Salir Sesion");
        salirSesionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirSesionLabelMouseClicked(evt);
            }
        });

        salirIIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconoSALIR.png"))); // NOI18N
        salirIIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirIIconMouseClicked(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(0, 51, 102));
        btnSalir.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(51, 204, 255));
        lblFecha.setText("Hora de conexion:");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(salirIIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(correoSesionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(salirSesionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAgencia)))
                .addGap(18, 18, 18)
                .addComponent(lblLogo)
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salirIIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAgencia)
                                .addComponent(salirSesionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correoSesionLabel)
                            .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLogo)))
                .addGap(11, 11, 11)
                .addComponent(tabEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void actualizarAnunciosPublicados() {
        inmueblesEncontradosList.removeAll();
        for (CatalogoAnuncios catalogo : inmobiliaria.getCatalogo()) {
            if (catalogo.getTipoCatalogo().equals(TipoCatalogo.PUBLICADOS)) {
                for (Anuncio a : catalogo.getAnuncios()) {
                    inmueblesEncontradosList.add(a.toString());
                }
            }
        }
    }
    private void btnRealizarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCambiosActionPerformed
        boolean realizado = true;
        for (int i = 0; i < inmobiliaria.getClientes().size(); i++) {
            if (inmobiliaria.getClientes().get(i).getCorreo().equals(cliente.getCorreo())) {
                if (txtCorreo.getText().contains("#") || txtApellidos.getText().contains("#") || txtPasswordActual.getText().contains("#")) {
                    lblError.setText("Hay un caracter no permitido");
                } else {
                    if (!inmobiliaria.getClientes().get(i).getPassword().equals(txtPasswordActual.getText())) {
                        lblError.setText("La contraseña no es correcta");
                    } else {
//modifico los datos del cliente
                        if (txtNombre.getText() != "" && txtNombre.getText().length() != 0) {
                            inmobiliaria.getClientes().get(i).setNombre(txtNombre.getText());
                        } else {
                            lblError.setText("El nombre no puede estar vacío");
                            realizado = false;
                        }
                        if (txtApellidos.getText() != "" && txtApellidos.getText().length() != 0) {
                            inmobiliaria.getClientes().get(i).setApellido(txtApellidos.getText());
                        } else {
                            lblError.setText("Los apellidos no pueden estar vacíos");
                            realizado = false;
                        }
                        if (txtCorreo.getText().contains("@")) {
                            if (txtCorreo.getText() != "" && txtCorreo.getText().length() != 0) {
                                inmobiliaria.getClientes().get(i).setCorreo(txtCorreo.getText());
                            } else {
                                lblError.setText("El correo no puede estar vacío");
                                realizado = false;
                            }
                        } else {
                            lblError.setText("El correo no es correcto.");
                            realizado = false;
                        }
                        if (txtPassword.getText().equals(txtRepetirPassword.getText())) {
                            if (txtPasswordActual.getText() != "" && txtPasswordActual.getText().length() != 0) {
                                inmobiliaria.getClientes().get(i).setPassword(txtPassword.getText());
                            } else {
                                lblError.setText("La contraseña nueva no puede estar vacía.");
                                realizado = false;
                            }
                        } else {
                            lblError.setText("Las contraseñas no coinciden.");
                            realizado = false;
                        }
                        if (realizado) {
                            lblError.setText("Se han realizado los cambios correctamente.");
                        }
                    }
                }
            }
        }
        file.saveToFileClientes(inmobiliaria);
    }//GEN-LAST:event_btnRealizarCambiosActionPerformed

    public void showInfoInmueble(Anuncio i) {
        infoInmuebleList1.setText("Nombre: " + i.getTitulo()
                + "\n" + "Descripción: " + i.getDescripcion()
                + "\n" + "Precio: " + i.getPrecio()
                + "\n" + "Tipo: " + i.getInmueble().getTipoInmueble().toString()
                + "\n" + "Superficie: " + i.getInmueble().getSuperficie().toString()
                + "\n" + "Numero habitaciones: " + Integer.toString(i.getInmueble().getNumeroHabitaciones())
                + "\n" + "Número de cuartos de baño: " + i.getInmueble().getNumeroDeWc()
                + "\n" + "Estado inmueble: " + i.getInmueble().getTipoEstadoInmueble().toString()
                + "\n" + "Direccion: " + i.getInmueble().getDireccion().toString());
    }

    private void compraRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraRadioButtonActionPerformed

    }//GEN-LAST:event_compraRadioButtonActionPerformed

    private void alquilerRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alquilerRadioButtonActionPerformed

    }//GEN-LAST:event_alquilerRadioButtonActionPerformed

    private void municipioRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_municipioRadioButtonActionPerformed
        precioDesdeTextField.setEnabled(false);
        precioHastaTextField.setEnabled(false);
        precioDesdeTextField.setText("");
        precioHastaTextField.setText("");
        municipiosTextField.setEnabled(true);

    }//GEN-LAST:event_municipioRadioButtonActionPerformed

    private void precioRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioRadioButtonActionPerformed
        precioDesdeTextField.setEnabled(true);
        precioHastaTextField.setEnabled(true);
        municipiosTextField.setEnabled(false);

    }//GEN-LAST:event_precioRadioButtonActionPerformed

    private void realizarBusquedaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarBusquedaButtonActionPerformed
        List<Anuncio> anunciosFiltro = new ArrayList<>();
        // Seleccion por municipio
        inmueblesEncontradosList.removeAll();
        if (municipioRadioButton.isSelected()) {
            // Inmuebles de compra
            if (alquilerRadioButton.isSelected()) {
                anunciosFiltro = getListAnunciosAlquilados(getListAnunciosPorMunicipio(municipiosTextField.getSelectedItem().toString()));
                // Inmuebles de alquiler
            } else {
                anunciosFiltro = getListAnunciosVendidos(getListAnunciosPorMunicipio(municipiosTextField.getSelectedItem().toString()));
            }
        }

        // Seleccion por precio
        if (precioRadioButton.isSelected()) {
            // Inmuebles de compra
            if (alquilerRadioButton.isSelected()) {
                anunciosFiltro = getListAnunciosAlquilados(getListAnunciosEntrePrecio(Integer.parseInt(precioDesdeTextField.getText()), Integer.parseInt(precioHastaTextField.getText())));
                // Inmuebles de alquiler
            } else {
                anunciosFiltro = getListAnunciosVendidos(getListAnunciosEntrePrecio(Integer.parseInt(precioDesdeTextField.getText()), Integer.parseInt(precioHastaTextField.getText())));
            }
        }
        for (Anuncio anuncios : anunciosFiltro) {
            inmueblesEncontradosList.add(anuncios.toString());
        }
    }//GEN-LAST:event_realizarBusquedaButtonActionPerformed

    public Cliente busquedadCliente(String cadena) {
        Cliente clienteBuscado = null;
        for (Cliente clientes : inmobiliaria.getClientes()) {
            for (Anuncio anuncios : clientes.getAnuncios()) {
                if (anuncios.toString().equals(cadena)) {
                    clienteBuscado = clientes;
                }
            }
        }
        return clienteBuscado;
    }

    private void contactarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactarButtonActionPerformed
        Mensaje m = new Mensaje(cliente, inmueblesEncontradosList.getSelectedItem().toString() + "\n" + txtMensaje.getText());
        // añadir mensaje al destinatario inmueblesEncontradosList
        // recorreindo el fichero y cuando encuentre a alguien con la misma direccion
        // signicia que se su correo y que lo puedo enviar
        Cliente nuevo = busquedadCliente(inmueblesEncontradosList.getSelectedItem().toString());
        for (int i = 0; i < inmobiliaria.getClientes().size(); i++) {
            if (inmobiliaria.getClientes().get(i).getCorreo().equals(nuevo.getCorreo())) {
                inmobiliaria.getClientes().get(i).enviarMensaje(m);
                file.saveToFileMensaje(inmobiliaria);
            }
        }
    }//GEN-LAST:event_contactarButtonActionPerformed

    private void txtPasswordActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActualActionPerformed

    private void salirSesionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirSesionLabelMouseClicked
        AgenciaForm agenciaForm = new AgenciaForm();
        agenciaForm.inmobiliaria = inmobiliaria;
        agenciaForm.file = file;
        agenciaForm.setLocationRelativeTo(null);
        agenciaForm.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_salirSesionLabelMouseClicked

    private void salirIIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirIIconMouseClicked
        AgenciaForm agenciaForm = new AgenciaForm();
        agenciaForm.inmobiliaria = inmobiliaria;
        agenciaForm.file = file;
        agenciaForm.setLocationRelativeTo(null);
        agenciaForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_salirIIconMouseClicked

    private void precioDesdeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioDesdeTextFieldKeyReleased
        if (precioDesdeTextField.getText().equals("")) {
            realizarBusquedaButton.setEnabled(false);
        } else {
            if (precioDesdeTextField.getText().matches("\\d*") & precioHastaTextField.getText().matches("\\d*")) {
                habilitarBotonRealizarBusqueda();
            } else {
                realizarBusquedaButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_precioDesdeTextFieldKeyReleased

    private void precioHastaTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioHastaTextFieldKeyReleased
        if (precioDesdeTextField.getText().equals("")) {
            realizarBusquedaButton.setEnabled(false);
        } else {
            if (precioHastaTextField.getText().matches("\\d*") & precioDesdeTextField.getText().matches("\\d*")) {
                habilitarBotonRealizarBusqueda();
            } else {
                realizarBusquedaButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_precioHastaTextFieldKeyReleased

    private void precioDesdeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioDesdeTextFieldActionPerformed

    }//GEN-LAST:event_precioDesdeTextFieldActionPerformed

    private void precioHastaTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioHastaTextFieldKeyPressed
        if (precioDesdeTextField.getText().equals("")) {
            realizarBusquedaButton.setEnabled(false);
        }
    }//GEN-LAST:event_precioHastaTextFieldKeyPressed

    private void precioDesdeTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioDesdeTextFieldKeyPressed
        if (precioDesdeTextField.getText().equals("")) {
            realizarBusquedaButton.setEnabled(false);
        }
    }//GEN-LAST:event_precioDesdeTextFieldKeyPressed

    private void txtSuperficieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuperficieActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtSuperficieActionPerformed

    public String obtenerCantidadImagenesCliente() {
        int i = 0;// La cantidad de anuncios que tengo como cliente
        for (int j = 0; j < inmobiliaria.getClientes().size(); j++) {
            if (inmobiliaria.getClientes().get(j).getCorreo().equals(cliente.getCorreo())) {
                i = inmobiliaria.getClientes().get(j).getAnuncios().size();
            }

        }
        return String.valueOf(i);
    }

    int contador_tope = 0;

    public void longitud() {
        contador_tope = 0;
        for (Cliente clientes : inmobiliaria.getClientes()) {
            for (Anuncio anuncios : clientes.getAnuncios()) {
                contador_tope++;
            }
        }
        //contador_tope++;
    }

    private void btnAdjuntarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdjuntarImagenActionPerformed
        longitud();
        JFileChooser jf = new JFileChooser();
        jf.setMultiSelectionEnabled(false);//obtenerCantidadImagenesCliente()
        if (jf.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (!jf.getSelectedFile().toString().substring(jf.getSelectedFile().toString().length() - 4).equals(".png")) {
                rsdragdropfiles.RSDragDropFiles.setCopiar(jf.getSelectedFile().toString(), "src/imagenes/clientes/inmueble_cliente" + "_" + contador_tope + ".png");
                txtFotoInmueble.setText("imagenes/clientes/inmueble_cliente" + "_" + contador_tope + ".png");

                infoBuscarFotoErorr.setText("foto añadida correctamente");
            } else {
                infoBuscarFotoErorr.setText("La foto debe ser formato .png");
            }
        }
    }//GEN-LAST:event_btnAdjuntarImagenActionPerformed

    public boolean comprobar(Anuncio anuncioNuevo) {
        for (Cliente clientes : inmobiliaria.getClientes()) {
            for (Anuncio anuncios : clientes.getAnuncios()) {
                if (anuncios.getInmueble().getDireccion().getCalle().equals(anuncioNuevo.getInmueble().getDireccion().getCalle())
                        & anuncios.getInmueble().getDireccion().getNumero() == anuncioNuevo.getInmueble().getDireccion().getNumero()
                        & anuncios.getInmueble().getDireccion().getZipCode() == anuncioNuevo.getInmueble().getDireccion().getZipCode()
                        & anuncios.getInmueble().getDireccion().getMunicipio().equals(anuncioNuevo.getInmueble().getDireccion().getMunicipio())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void btnPublicarAnuncioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarAnuncioActionPerformed
        for (int i = 0; i < inmobiliaria.getClientes().size(); i++) {
            if (inmobiliaria.getClientes().get(i).getCorreo().equals(cliente.getCorreo())) {
                Inmueble inmueble = null;
                TipoInmueble tipoInmueble = null;
                switch (cbtipoInmueble.getSelectedItem().toString()) {
                    case "Piso":
                        tipoInmueble = TipoInmueble.PISO;
                        break;
                    case "Chalet":
                        tipoInmueble = TipoInmueble.CHALET;
                        break;
                    case "Apartamento":
                        tipoInmueble = TipoInmueble.APARTAMENTO;
                        break;
                    default:
                        tipoInmueble = TipoInmueble.CASA;
                        break;
                }

                TipoEstadoInmueble tipoEstadoInmueble = null;

                switch (cbEstadoInmueble.getSelectedItem().toString()) {
                    case "Pendiente a reformar":
                        tipoEstadoInmueble = TipoEstadoInmueble.PENDIENTEREFORMAR;
                        break;
                    case "Nueva obra":
                        tipoEstadoInmueble = TipoEstadoInmueble.NUEVAOBRA;
                        break;
                    default:
                        tipoEstadoInmueble = TipoEstadoInmueble.BUENESTADO;
                        break;
                }

                Direccion d = new Direccion(txtCalle.getText(), txtPiso.getText(), cbMunicipio.getSelectedItem().toString(), Integer.parseInt(txtNumero.getText()), Integer.parseInt(txCodigoPostal.getText()));

                inmueble = new Inmueble(tipoInmueble, Double.parseDouble(txtSuperficie.getText()), Integer.parseInt(cbNumHabitaciones.getSelectedItem().toString()), Integer.parseInt(cbNumeroBaños.getSelectedItem().toString()), tipoEstadoInmueble, d);

                TipoOperacion tipoOperacion = null;

                if (rbAlquiler.isSelected()) {
                    tipoOperacion = TipoOperacion.ALQUILER;
                } else {
                    tipoOperacion = TipoOperacion.VENTA;
                }

                Anuncio a = new Anuncio(inmueble, Integer.parseInt(txtPrecio.getText()), txtFotoInmueble.getText(), txtTituloAnuncio.getText(), txtAreaDescripcionAnuncio.getText(), tipoOperacion);

                boolean encontrado = false;

                for (int j = 0; j < inmobiliaria.getCatalogo().size(); j++) {
                    if (inmobiliaria.getCatalogo().get(j).getTipoCatalogo().equals(TipoCatalogo.PENDIENTES)) {
                        //añado el anuncio al catálogo de pendientes
                        inmobiliaria.getCatalogo().get(j).addAnuncios(a);
                        encontrado = true;
                    }
                }
                //guardo el anuncio en el catálogo
                //si no está creado el catálogo lo creo

                if (!encontrado) {
                    CatalogoAnuncios catalogoAnuncios = new CatalogoAnuncios(TipoCatalogo.PENDIENTES);
                    catalogoAnuncios.addAnuncios(a);
                    inmobiliaria.addCatalogo(catalogoAnuncios);
                }

                // guardo el anuncio en el cliente
                cliente.addAnuncios(a);

                file.saveToFileClientes(inmobiliaria);
                file.saveToFileCatalogo(inmobiliaria);
            }
        }
    }//GEN-LAST:event_btnPublicarAnuncioActionPerformed

    private void txtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalleActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtCalleActionPerformed

    private void txtPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPisoActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtPisoActionPerformed

    private void rbAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlquilerActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbAlquilerActionPerformed

    private void rbVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVentaActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbVentaActionPerformed

    private void txtRepetirPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRepetirPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRepetirPasswordActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtPasswordActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordActualKeyReleased
        btnRealizarCambios.setEnabled(true);
    }//GEN-LAST:event_txtPasswordActualKeyReleased

    private void cargarHistorialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarHistorialButtonActionPerformed
        String clientesInteresados = "";
        historialMensajesTextArea.removeAll();
        List<String> cop = new ArrayList<>();

        for (int i = 0; i < cliente.getFrom().size(); i++) {
            cop.add(cliente.getFrom().get(i).getFrom().toString());
        }

        List<String> deDup = cop.stream().distinct().collect(Collectors.toList());
        deDup.forEach((dup) -> historialMensajesTextArea.add(dup));
    }//GEN-LAST:event_cargarHistorialButtonActionPerformed

    private void txtCalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalleKeyReleased
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtCalleKeyReleased

    private void txCodigoPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodigoPostalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txCodigoPostalKeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void txtPisoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoKeyReleased

    private void txtSuperficieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSuperficieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSuperficieKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void rbAlquilerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbAlquilerKeyPressed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbAlquilerKeyPressed

    private void rbVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbVentaKeyPressed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbVentaKeyPressed

    private void historialMensajesTextAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialMensajesTextAreaActionPerformed
        showClient(historialMensajesTextArea.getSelectedItem());
    }//GEN-LAST:event_historialMensajesTextAreaActionPerformed

    private void cbMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMunicipioActionPerformed
        comprobarPublicarAnuncio();

    }//GEN-LAST:event_cbMunicipioActionPerformed

    private void txCodigoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCodigoPostalActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txCodigoPostalActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cbNumHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNumHabitacionesActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_cbNumHabitacionesActionPerformed

    private void cbtipoInmuebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtipoInmuebleActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_cbtipoInmuebleActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void rbAlquilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAlquilerMouseClicked
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbAlquilerMouseClicked

    private void rbVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbVentaMouseClicked
        comprobarPublicarAnuncio();
    }//GEN-LAST:event_rbVentaMouseClicked

    private void txtFotoInmuebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFotoInmuebleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFotoInmuebleActionPerformed

    private void inmueblesEncontradosListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inmueblesEncontradosListActionPerformed
        Anuncio aux = null;
        for (CatalogoAnuncios catalogo : inmobiliaria.getCatalogo()) {
            for (Anuncio a : catalogo.getAnuncios()) {
                if (a.toString().equals(inmueblesEncontradosList.getSelectedItem().toString())) {
                    aux = a;
                }
            }
        }

        if (aux != null) {
            showInfoInmueble(aux);
        }
        contactarButton.setEnabled(true);
    }//GEN-LAST:event_inmueblesEncontradosListActionPerformed

    private void municipiosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_municipiosTextFieldActionPerformed
        realizarBusquedaButton.setEnabled(true);
    }//GEN-LAST:event_municipiosTextFieldActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void cbNumeroBañosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNumeroBañosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNumeroBañosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actualizarAnunciosPublicados();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbEstadoInmuebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoInmuebleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoInmuebleActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteForm().setVisible(true);
            }
        });
    }

    public void establecerDatos() {
        txtNombre.setText(cliente.getNombre());
        txtApellidos.setText(cliente.getApellido());
        txtCorreo.setText(cliente.getCorreo());
        txtPassword.setText(cliente.getPassword());
        txtRepetirPassword.setText(cliente.getPassword());
        correoSesionLabel.setText(cliente.getCorreo());
    }

    public void habilitarBotonRealizarBusqueda() {
        if (municipioRadioButton.isSelected() ^ precioRadioButton.isSelected()) {
            if (compraRadioButton.isSelected() ^ alquilerRadioButton.isSelected()) {
                if (precioDesdeTextField.getText().matches("\\d*") & precioHastaTextField.getText().matches("\\d*")
                        & precioDesdeTextField.getText().length() != 0 & precioHastaTextField.getText().length() != 0) {
                    realizarBusquedaButton.setEnabled(true);
                } else {
                    realizarBusquedaButton.setEnabled(false);
                }
            } else {
                realizarBusquedaButton.setEnabled(false);
            }
        } else {
            realizarBusquedaButton.setEnabled(false);
        }
    }

    public void habilitarBotonContratarRecomendacion() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InmueblesEncontradosLabel;
    private javax.swing.JRadioButton alquilerRadioButton;
    private javax.swing.JLabel apellidosLabel;
    private javax.swing.JButton btnAdjuntarImagen;
    private javax.swing.JButton btnPublicarAnuncio;
    private javax.swing.JButton btnRealizarCambios;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel buscarInmueblePanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cargarHistorialButton;
    private javax.swing.JComboBox<String> cbEstadoInmueble;
    private javax.swing.JComboBox<String> cbMunicipio;
    private javax.swing.JComboBox<String> cbNumHabitaciones;
    private javax.swing.JComboBox<String> cbNumeroBaños;
    private javax.swing.JComboBox<String> cbtipoInmueble;
    private javax.swing.JRadioButton compraRadioButton;
    private javax.swing.JButton contactarButton;
    private javax.swing.JLabel contraseñaNuevaLabel;
    private javax.swing.JLabel correoSesionLabel;
    private javax.swing.JLabel datosContactoLabel;
    private javax.swing.JLabel filtrosBusquedaLabel;
    private javax.swing.ButtonGroup filtrosButtonGroup;
    private javax.swing.JPanel historialMensajesPanel;
    private java.awt.List historialMensajesTextArea;
    private javax.swing.JLabel infoAñadirInmuebleLabel;
    private javax.swing.JLabel infoBuscarFotoErorr;
    private javax.swing.JLabel infoBuscarInmuebleLabel;
    private javax.swing.JTextArea infoContactoTextArea;
    private javax.swing.JLabel infoEditarPerfilLabel;
    private javax.swing.JLabel infoHistorialLabel;
    private javax.swing.JLabel infoIngreseContraseñaLabel;
    private javax.swing.JTextArea infoInmuebleList1;
    private javax.swing.JLabel informacionDelInmuebleLabel;
    private javax.swing.ButtonGroup inmuebleParaButtonGroup;
    private javax.swing.JLabel inmuebleparaLabel;
    private java.awt.List inmueblesEncontradosList;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblCodigoPostal;
    private javax.swing.JLabel lblCorreoElectronico;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblErrorAñadirAnuncio;
    private javax.swing.JLabel lblEstadoInmueble;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFotoInmueble;
    private javax.swing.JLabel lblInfoDelInmu;
    private javax.swing.JLabel lblInfoDireccion;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblNumeroBaños;
    private javax.swing.JLabel lblNumeroHabitaciones;
    private javax.swing.JLabel lblNumeroInmueble;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPreferencias;
    private javax.swing.JLabel lblSuperficie;
    private javax.swing.JLabel lblTipoInmueble;
    private javax.swing.JLabel lblTituloAnuncio;
    private javax.swing.JLabel municipioLabel;
    private javax.swing.JRadioButton municipioRadioButton;
    private javax.swing.JComboBox<String> municipiosTextField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel pIsoLabel;
    private javax.swing.JPanel panelAñadir;
    private javax.swing.JPanel panelAñadirAnuncio;
    private javax.swing.JPanel panelBuscarInmueble;
    private javax.swing.JPanel panelEditarPerfil;
    private javax.swing.JPanel panelHistorial;
    private javax.swing.JPanel panelmodificar;
    private javax.swing.JLabel personasInteresadasLabel;
    private javax.swing.ButtonGroup precioButtonGroup;
    private javax.swing.JLabel precioDesdeLabel;
    private javax.swing.JTextField precioDesdeTextField;
    private javax.swing.JLabel precioHastaLabel;
    private javax.swing.JTextField precioHastaTextField;
    private javax.swing.JRadioButton precioRadioButton;
    private javax.swing.JRadioButton rbAlquiler;
    private javax.swing.JRadioButton rbVenta;
    private javax.swing.JButton realizarBusquedaButton;
    private javax.swing.JLabel repetirContraseñaLabel;
    private javax.swing.JLabel salirIIcon;
    private javax.swing.JLabel salirSesionLabel;
    private javax.swing.JTabbedPane tabEditarPerfil;
    private javax.swing.JTextField txCodigoPostal;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextArea txtAreaDescripcionAnuncio;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtFotoInmueble;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordActual;
    private javax.swing.JTextField txtPiso;
    private java.awt.TextField txtPrecio;
    private javax.swing.JPasswordField txtRepetirPassword;
    private java.awt.TextField txtSuperficie;
    private javax.swing.JTextField txtTituloAnuncio;
    // End of variables declaration//GEN-END:variables
}
