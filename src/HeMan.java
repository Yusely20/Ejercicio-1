import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HeMan {
    private JPanel pGeneral;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JComboBox comboBoxHabilidad;
    private JComboBox comboBoxNivel;
    private JComboBox comboBoxRegion;
    private JTable JTableRegistro;
    private JTextField textFieldIDBuscar;
    private JButton buscarButton;
    private JComboBox comboBoxFiltroHabilidad;
    private JTable tableFiltrar;
    private JButton filtrarButton;
    private JButton conteoButton;
    private JTextArea textAreaConteo;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JPanel pModificar;
    private JButton modificarButton;
    private JTextField textFieldIdModificar;
    private JTextField textFieldNombreModificar;
    private JComboBox comboBoxHabilidadModificar;
    private JComboBox comboBoxNivelModificar;
    private JComboBox comboBoxRegionModificar;
    private  JComboBox comboBoxHabilidadConteo;

    private ListaSimple lista = new ListaSimple();

    public HeMan() {
        DefaultTableModel df = new DefaultTableModel(new Object[]{"ID", "Nombre", "Habilidad", "Nivel", "Región"}, 0);
        JTableRegistro.setModel(df);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldID.getText().trim());
                    String nombre = textFieldNombre.getText().trim();
                    String habilidad = comboBoxHabilidad.getSelectedItem().toString();
                    int nivel = Integer.parseInt(comboBoxNivel.getSelectedItem().toString());
                    String region = comboBoxRegion.getSelectedItem().toString();

                    DefensorEternia nuevo = new DefensorEternia(id, nombre, habilidad, nivel, region);
                    lista.agregarNodo(nuevo);
                    JOptionPane.showMessageDialog(null, "Defensor registrado exitosamente");
                    actualizarTabla();
                    limpiarFormulario();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. Revisa los campos.");
                }
            }
        });

        limpiarButton.addActionListener(e -> limpiarFormulario());

        buscarButton.addActionListener(e -> {
            try {
                int idBuscar = Integer.parseInt(textFieldIDBuscar.getText().trim());
                BusquedaLineal busqueda = new BusquedaLineal();
                DefensorEternia encontrado = busqueda.buscarLineal(lista, idBuscar);
                if (encontrado != null) {
                    cargarDatosAModificar(encontrado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró defensor con ese ID");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ID inválido");
            }
        });

        filtrarButton.addActionListener(e -> {
            String regionSeleccionada = comboBoxFiltroHabilidad.getSelectedItem().toString();
            Burbuja orden = new Burbuja();
            ArrayList<Nodo> listaOrdenada = orden.bubbleSort(lista, regionSeleccionada);

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Habilidad", "Nivel", "Región"});
            for (Nodo nodo : listaOrdenada) {
                DefensorEternia g = nodo.dato;
                modelo.addRow(new Object[]{g.getId(), g.getNombre(), g.getHabilidadEspecial(), g.getNivelPoder(), g.getRegion()});
            }
            tableFiltrar.setModel(modelo);
        });

        conteoButton.addActionListener(e -> {
            Recursividad rec = new Recursividad();
            textAreaConteo.setText(rec.contar(lista).toString());
        });

        modificarButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdModificar.getText().trim());
                String nombre = textFieldNombreModificar.getText().trim();
                String habilidad = comboBoxHabilidadModificar.getSelectedItem().toString();
                int nivel = Integer.parseInt(comboBoxNivelModificar.getSelectedItem().toString());
                String region = comboBoxRegionModificar.getSelectedItem().toString();

                Nodo actual = lista.getCabeza();
                while (actual != null) {
                    if (actual.dato.getId() == id) {
                        actual.dato.setNombre(nombre);
                        actual.dato.setHabilidadEspecial(habilidad);
                        actual.dato.setNivelPoder(nivel);
                        actual.dato.setRegion(region);
                        JOptionPane.showMessageDialog(null, "Defensor modificado exitosamente");
                        actualizarTabla();
                        return;
                    }
                    actual = actual.siguiente;
                }

                JOptionPane.showMessageDialog(null, "No se encontró defensor con ese ID");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos inválidos. Revisa los campos.");
            }
        });
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) JTableRegistro.getModel();
        modelo.setRowCount(0);
        Nodo actual = lista.getCabeza();
        while (actual != null) {
            DefensorEternia g = actual.dato;
            modelo.addRow(new Object[]{g.getId(), g.getNombre(), g.getHabilidadEspecial(), g.getNivelPoder(), g.getRegion()});
            actual = actual.siguiente;
        }
    }

    private void limpiarFormulario() {
        textFieldID.setText("");
        textFieldNombre.setText("");
        comboBoxHabilidad.setSelectedIndex(0);
        comboBoxNivel.setSelectedIndex(0);
        comboBoxRegion.setSelectedIndex(0);
    }

    private void cargarDatosAModificar(DefensorEternia g) {
        textFieldIdModificar.setText(String.valueOf(g.getId()));
        textFieldIdModificar.setEditable(false);
        textFieldNombreModificar.setText(g.getNombre());
        comboBoxHabilidadModificar.setSelectedItem(g.getHabilidadEspecial());
        comboBoxNivelModificar.setSelectedItem(String.valueOf(g.getNivelPoder()));
        comboBoxRegionModificar.setSelectedItem(g.getRegion());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Defensores de Eternia");
        frame.setContentPane(new HeMan().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}