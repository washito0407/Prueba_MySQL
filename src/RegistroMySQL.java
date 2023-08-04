import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistroMySQL {
    static final String DB_URL="jdbc:mysql://localhost/estudiantes";
    static final String USER="root";
    static final String PASS="";
    static final String QUERY="SELECT * FROM registro2";
    private JPanel panel1;
    private JPanel Registro_personas;
    private JTextField codigoTextField;
    private JTextField cedulaTextField;
    private JTextField nombreTextField;
    private JTextField fechanacTextField;
    private JComboBox comboBox1;
    private JButton botonBuscarPorCodigoButton;
    private JButton botonBuscarPorNombreButton;
    private JButton botonBuscarPorSignoButton;
    private JButton botonBorrarElPresenteButton;
    private JButton botonActualizarElSiguietneButton;
    private JButton botonIngresarElPresenteButton;
    private JButton limpiarFormularioButton;
    public RegistroMySQL () {
        this.codigoTextField = codigoTextField;
        this.cedulaTextField = cedulaTextField;
        this.nombreTextField = nombreTextField;
        this.fechanacTextField = fechanacTextField;
        this.comboBox1 = comboBox1;

        botonIngresarElPresenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonIngresarElPresenteButtonActionPerformed(e);
            }
        });
    }

    private void botonIngresarElPresenteButtonActionPerformed(ActionEvent e) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = conn.prepareStatement(QUERY);
            statement.setString(1, codigoTextField.getText());
            statement.setString(2, cedulaTextField.getText());
            statement.setString(3, nombreTextField.getText());
            statement.setString(4, fechanacTextField.getText());
            statement.setString(5, comboBox1.getSelectedItem().toString());

            statement.executeUpdate();

            statement.close();
            conn.close();

            // Limpiar los campos después de insertar los datos
            codigoTextField.setText("");
            cedulaTextField.setText("");
            nombreTextField.setText("");
            fechanacTextField.setText("");
            comboBox1.setSelectedIndex(0);

            System.out.println("Datos insertados correctamente en la base de datos.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }

    private void botonBuscarPorCodigoButton() {
        String codigo = codigoTextField.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = conn.prepareStatement(QUERY);
            statement.setString(1, codigo);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                cedulaTextField.setText(result.getString("cedula"));
                nombreTextField.setText(result.getString("nombre"));
                fechanacTextField.setText(result.getString("fechanac"));
                comboBox1.setSelectedItem(result.getString("signo"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados para el código: " + codigo);
            }

            result.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIngresarElPresenteButton) {
            // ... (código para ingresar datos)
        } else if (e.getSource() == botonBuscarPorCodigoButton) {
            buscarPorCodigo();
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("RegistroMySQL");
        frame.setContentPane(new RegistroMySQL().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


