package udistrital.pacientedao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import udistrital.pacientedao.db.DBConnection;
import udistrital.pacientedao.db.PostgreSQLConnection;
import udistrital.pacientedao.modelo.Paciente;

/**
 * Simple GUI application that provides CRUD operations for Paciente.
 */
public class PacienteApp {

    private final udistrital.pacientedao.dao.PacienteDAO dao;
    private final JFrame frame;
    private final JTable table;
    private final DefaultTableModel model;

    private final JTextField cedulaField = new JTextField();
    private final JTextField fechaField = new JTextField();
    private final JTextField pNombreField = new JTextField();
    private final JTextField sNombreField = new JTextField();
    private final JTextField pApellidoField = new JTextField();
    private final JTextField sApellidoField = new JTextField();

    public PacienteApp() {
        DBConnection con = new PostgreSQLConnection();
        dao = new udistrital.pacientedao.dao.PacienteDAO(con);

        frame = new JFrame("Pacientes");
        model = new DefaultTableModel(new Object[]{"Cedula", "FechaNac", "PrimerN", "SegundoN", "PrimerA", "SegundoA"}, 0);
        table = new JTable(model);

        JPanel form = new JPanel(new GridLayout(6, 2));
        form.add(new JLabel("Cedula"));
        form.add(cedulaField);
        form.add(new JLabel("FechaNac"));
        form.add(fechaField);
        form.add(new JLabel("Primer Nombre"));
        form.add(pNombreField);
        form.add(new JLabel("Segundo Nombre"));
        form.add(sNombreField);
        form.add(new JLabel("Primer Apellido"));
        form.add(pApellidoField);
        form.add(new JLabel("Segundo Apellido"));
        form.add(sApellidoField);

        JButton createBtn = new JButton("Crear");
        JButton updateBtn = new JButton("Actualizar");
        JButton deleteBtn = new JButton("Eliminar");
        JButton refreshBtn = new JButton("Refrescar");

        JPanel buttons = new JPanel();
        buttons.add(createBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);

        frame.setLayout(new BorderLayout());
        frame.add(form, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
                refrescar();
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
                refrescar();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
                refrescar();
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescar();
            }
        });

        refrescar();
    }

    private Paciente fromFields() {
        return new Paciente(
                Long.parseLong(cedulaField.getText()),
                OffsetDateTime.parse(fechaField.getText()),
                pNombreField.getText(),
                sNombreField.getText(),
                pApellidoField.getText(),
                sApellidoField.getText()
        );
    }

    private void crear() {
        try {
            dao.crear(fromFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizar() {
        try {
            dao.actualizar(fromFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminar() {
        try {
            dao.eliminar(Long.parseLong(cedulaField.getText()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescar() {
        model.setRowCount(0);
        try {
            for (Object obj : dao.listarTodos()) {
                Paciente p = (Paciente) obj;
                model.addRow(new Object[]{
                    p.getCedula(),
                    p.getFechaNac(),
                    p.getPrimerNombre(),
                    p.getSegundoNombre(),
                    p.getPrimerApellido(),
                    p.getSegundoApellido()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        new PacienteApp().mostrar();
    }
}

