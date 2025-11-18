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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import udistrital.pacientedao.db.DBConnection;
import udistrital.pacientedao.db.PostgreSQLConnection;
import udistrital.pacientedao.modelo.Paciente;
import udistrital.pacientedao.dao.FarmaciaDAO;
import udistrital.pacientedao.modelo.Farmacia;
import udistrital.pacientedao.dao.MedicinaDAO;
import udistrital.pacientedao.modelo.Medicina;

/**
 * Simple GUI application that provides CRUD operations for Paciente.
 */
public class PacienteApp {

    private final udistrital.pacientedao.dao.PacienteDAO dao;
    private final FarmaciaDAO farmaciaDao;
    private final MedicinaDAO medicinaDao;

    private final JFrame frame;
    private final JTable table;
    private final DefaultTableModel model;

    private final JTable farmaciaTable;
    private final DefaultTableModel farmaciaModel;

    private final JTable medicinaTable;
    private final DefaultTableModel medicinaModel;

    private final JTextField cedulaField = new JTextField();
    private final JTextField fechaField = new JTextField();
    private final JTextField pNombreField = new JTextField();
    private final JTextField sNombreField = new JTextField();
    private final JTextField pApellidoField = new JTextField();
    private final JTextField sApellidoField = new JTextField();

    private final JTextField idFarmaciaField = new JTextField();
    private final JTextField nombreFarmaciaField = new JTextField();
    private final JTextField calleField = new JTextField();
    private final JTextField carreraField = new JTextField();
    private final JTextField numeroField = new JTextField();

    private final JTextField idMedicinaField = new JTextField();
    private final JTextField nombreGenericoField = new JTextField();

    public PacienteApp() {
        DBConnection con = PostgreSQLConnection.getConnector();
        dao = new udistrital.pacientedao.dao.PacienteDAO(con);
        farmaciaDao = new FarmaciaDAO(con);
        medicinaDao = new MedicinaDAO(con);

        frame = new JFrame("Hospital");

        model = new DefaultTableModel(new Object[]{"Cedula", "FechaNac", "PrimerN", "SegundoN", "PrimerA", "SegundoA"}, 0);
        table = new JTable(model);
        farmaciaModel = new DefaultTableModel(new Object[]{"Id", "Nombre", "Calle", "Carrera", "Numero"}, 0);
        farmaciaTable = new JTable(farmaciaModel);
        medicinaModel = new DefaultTableModel(new Object[]{"Id", "Nombre Genérico"}, 0);
        medicinaTable = new JTable(medicinaModel);

        JTabbedPane tabs = new JTabbedPane();

        // Panel Pacientes
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

        JPanel pacientePanel = new JPanel(new BorderLayout());
        pacientePanel.add(form, BorderLayout.NORTH);
        pacientePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        pacientePanel.add(buttons, BorderLayout.SOUTH);

        // Panel Farmacia
        JPanel farmaciaForm = new JPanel(new GridLayout(5, 2));
        farmaciaForm.add(new JLabel("Id"));
        farmaciaForm.add(idFarmaciaField);
        farmaciaForm.add(new JLabel("Nombre"));
        farmaciaForm.add(nombreFarmaciaField);
        farmaciaForm.add(new JLabel("Calle"));
        farmaciaForm.add(calleField);
        farmaciaForm.add(new JLabel("Carrera"));
        farmaciaForm.add(carreraField);
        farmaciaForm.add(new JLabel("Numero"));
        farmaciaForm.add(numeroField);

        JButton createFBtn = new JButton("Crear");
        JButton updateFBtn = new JButton("Actualizar");
        JButton deleteFBtn = new JButton("Eliminar");
        JButton refreshFBtn = new JButton("Refrescar");

        JPanel farmaciaButtons = new JPanel();
        farmaciaButtons.add(createFBtn);
        farmaciaButtons.add(updateFBtn);
        farmaciaButtons.add(deleteFBtn);
        farmaciaButtons.add(refreshFBtn);

        JPanel farmaciaPanel = new JPanel(new BorderLayout());
        farmaciaPanel.add(farmaciaForm, BorderLayout.NORTH);
        farmaciaPanel.add(new JScrollPane(farmaciaTable), BorderLayout.CENTER);
        farmaciaPanel.add(farmaciaButtons, BorderLayout.SOUTH);

        // Panel Medicina
        JPanel medicinaForm = new JPanel(new GridLayout(2, 2));
        medicinaForm.add(new JLabel("Id"));
        medicinaForm.add(idMedicinaField);
        medicinaForm.add(new JLabel("Nombre Genérico"));
        medicinaForm.add(nombreGenericoField);

        JButton createMBtn = new JButton("Crear");
        JButton updateMBtn = new JButton("Actualizar");
        JButton deleteMBtn = new JButton("Eliminar");
        JButton refreshMBtn = new JButton("Refrescar");

        JPanel medicinaButtons = new JPanel();
        medicinaButtons.add(createMBtn);
        medicinaButtons.add(updateMBtn);
        medicinaButtons.add(deleteMBtn);
        medicinaButtons.add(refreshMBtn);

        JPanel medicinaPanel = new JPanel(new BorderLayout());
        medicinaPanel.add(medicinaForm, BorderLayout.NORTH);
        medicinaPanel.add(new JScrollPane(medicinaTable), BorderLayout.CENTER);
        medicinaPanel.add(medicinaButtons, BorderLayout.SOUTH);

        tabs.addTab("Pacientes", pacientePanel);
        tabs.addTab("Farmacias", farmaciaPanel);
        tabs.addTab("Medicina", medicinaPanel);

        frame.setLayout(new BorderLayout());
        frame.add(tabs, BorderLayout.CENTER);
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

        createFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearFarmacia();
                refrescarFarmacia();
            }
        });

        updateFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFarmacia();
                refrescarFarmacia();
            }
        });

        deleteFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFarmacia();
                refrescarFarmacia();
            }
        });

        refreshFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarFarmacia();
            }
        });

        createMBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearMedicina();
                refrescarMedicina();
            }
        });

        updateMBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMedicina();
                refrescarMedicina();
            }
        });

        deleteMBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMedicina();
                refrescarMedicina();
            }
        });

        refreshMBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarMedicina();
            }
        });

        refrescar();
        refrescarFarmacia();
        refrescarMedicina();
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

    private Farmacia fromFarmaciaFields() {
        return new Farmacia(
                Integer.parseInt(idFarmaciaField.getText()),
                nombreFarmaciaField.getText(),
                calleField.getText(),
                carreraField.getText(),
                numeroField.getText()
        );
    }

    private void crearFarmacia() {
        try {
            farmaciaDao.crear(fromFarmaciaFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarFarmacia() {
        try {
            farmaciaDao.actualizar(fromFarmaciaFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarFarmacia() {
        try {
            farmaciaDao.eliminar(Integer.parseInt(idFarmaciaField.getText()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescarFarmacia() {
        farmaciaModel.setRowCount(0);
        try {
            for (Object obj : farmaciaDao.listarTodos()) {
                Farmacia f = (Farmacia) obj;
                farmaciaModel.addRow(new Object[]{
                    f.getIdFarmacia(),
                    f.getNombre(),
                    f.getCalle(),
                    f.getCarrera(),
                    f.getNumero()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Medicina fromMedicinaFields() {
        return new Medicina(
                Integer.parseInt(idMedicinaField.getText()),
                nombreGenericoField.getText()
        );
    }

    private void crearMedicina() {
        try {
            medicinaDao.crear(fromMedicinaFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarMedicina() {
        try {
            medicinaDao.actualizar(fromMedicinaFields());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarMedicina() {
        try {
            medicinaDao.eliminar(Integer.parseInt(idMedicinaField.getText()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void refrescarMedicina() {
        medicinaModel.setRowCount(0);
        try {
            for (Object obj : medicinaDao.listarTodos()) {
                Medicina m = (Medicina) obj;
                medicinaModel.addRow(new Object[]{
                    m.getIdMedicina(),
                    m.getNombreGenerico()
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

