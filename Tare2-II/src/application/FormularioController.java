package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import clases.Area;
import clases.Normal;
import clases.Profesion;
import clases.Supervisor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FormularioController  implements Initializable{
	
	private ObservableList<Normal> empleados;
	private ObservableList<Supervisor> supervisores;
	private ObservableList<Profesion> profesiones;
	@FXML private TextField txtNombre;
	@FXML private TextField txtApellido;
	@FXML private TextField txtEdad;
	@FXML private TextField txtSalario;
	@FXML private ComboBox<Profesion> cboProfesion;
	@FXML private ComboBox<Supervisor> cboSupervisor;
	@FXML private ToggleGroup genero;
	@FXML private ToggleGroup status;
	@FXML private RadioButton rbtFem;
	@FXML private RadioButton rbtMas;
	@FXML private RadioButton rbtContratado;
	@FXML private RadioButton rbtDespedido;
	@FXML private Button btnNuevo;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;
	@FXML private ListView<Normal> lstEmpleados;
	
	private ArrayList<String> errores;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		empleados = FXCollections.observableArrayList();
		supervisores = FXCollections.observableArrayList();
		profesiones = FXCollections.observableArrayList();
		errores = new ArrayList<String>();
		
		supervisores.add(new Supervisor("Macario", "Mendoza", "30", "Masculino", "15", new Area("005", "Recursos Humanos")));
		supervisores.add(new Supervisor("Enrique", "Gonzales", "35", "Masculino", "10", new Area("002", "Informatica")));
		supervisores.add(new Supervisor("Daneli", "Buezo", "32", "Femenino", "5", new Area("003", "Administracion")));
		
		cboSupervisor.setItems(supervisores);
		
		profesiones.add(new Profesion("A001", "Lic. en Administracion de Empresas"));
		profesiones.add(new Profesion("S001", "Ing. en Sistemas"));
		profesiones.add(new Profesion("D001", "Lic. en Derecho"));
		
		cboProfesion.setItems(profesiones);
		
		lstEmpleados.setItems(empleados);
		
		lstEmpleados.getSelectionModel().selectedItemProperty().addListener(
				 
				new ChangeListener<Normal>() {

					@Override
					public void changed(ObservableValue<? extends Normal> observable, Normal oldValue,
							Normal valorActual) {
						if (valorActual != null) {
							
							txtNombre.setText(valorActual.getNombre());
							txtApellido.setText(valorActual.getApellido());
							txtEdad.setText(valorActual.getEdad());
							txtSalario.setText(valorActual.getSalario());
							
							if (valorActual.getGenero().equals("Femenino"))
								rbtFem.setSelected(true);
							if (valorActual.getGenero().equals("Masculino"))
								rbtMas.setSelected(true);
							
							if (valorActual.getEstado().equals("Contratado"))
								rbtContratado.setSelected(true);
							if (valorActual.getEstado().equals("Despedido"))
								rbtDespedido.setSelected(true);
							
							cboProfesion.getSelectionModel().select(valorActual.getProfesion());
							cboSupervisor.getSelectionModel().select(valorActual.getSupervisor());
							
						}
						
						
						btnGuardar.setDisable(true);
						btnActualizar.setDisable(false);
						
					}
					
				}
				);
		leerArchivo();
	}
	
	public void leerArchivo() {
		
		try {
			ObjectInputStream archivo= new ObjectInputStream(new FileInputStream("archivo.data"));
			try {
				while(true) {
					Normal a = (Normal)archivo.readObject();
					empleados.add(a);
				}
			}catch (EOFException e) {
				System.out.println("Fin del archivo");
			}
			archivo.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public void guardar() {
		
		validar();
		if (errores.size()>0) {
			
			Alert mensaje = new Alert(AlertType.WARNING);
			String cadenaErrores = "";
			
			for (int i = 0; i < errores.size(); i++) 
				cadenaErrores += errores.get(i) + "\n";
			
			mensaje.setTitle("Error al guardar");
			mensaje.setContentText(cadenaErrores);
			mensaje.setHeaderText("Se econtraron los siguientes errores: ");
			mensaje.show();
			return;
		}
		
		Normal empleado =  new Normal(
				
				txtNombre.getText(),
				txtApellido.getText(),
				txtEdad.getText(),
				((RadioButton)genero.getSelectedToggle()).getText(),
				cboProfesion.getSelectionModel().getSelectedItem(),
				cboSupervisor.getSelectionModel().getSelectedItem(),
				txtSalario.getText(),
				((RadioButton)status.getSelectedToggle()).getText()
				
				);
		
		empleados.add(empleado);
		guardarArchivo();
		nuevo();
		
	}
	
	public void guardarArchivo() {
		
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("archivo.data"));
			for (int i = 0; i < empleados.size(); i++) {
				archivo.writeObject(empleados.get(i));
			}
			archivo.flush();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void actualizar() {
		
		empleados.set(lstEmpleados.getSelectionModel().getSelectedIndex(),
				
				new Normal(txtNombre.getText(),
						txtApellido.getText(),
						txtEdad.getText(),
						((RadioButton)genero.getSelectedToggle()).getText(),
						cboProfesion.getSelectionModel().getSelectedItem(),
						cboSupervisor.getSelectionModel().getSelectedItem(),
						txtSalario.getText(),
						((RadioButton)status.getSelectedToggle()).getText()
						)
				);
		
	}
	
	public void nuevo() {
		
		txtNombre.clear();
		txtApellido.clear();
		txtEdad.clear();
		txtSalario.clear();
		cboProfesion.getSelectionModel().clearSelection();
		cboSupervisor.getSelectionModel().clearSelection();
		btnActualizar.setDisable(true);
		btnGuardar.setDisable(false);
		
	}

	public void validar() {
		errores.clear();
		
		if (txtNombre.getText().isEmpty())
			errores.add("El campo Nombre esta vacio");
		if (txtApellido.getText().isEmpty())
			errores.add("El campo Apellido esta vacio");
		if (txtEdad.getText().isEmpty())
			errores.add("El campo Edad esta vacio");
		if (txtSalario.getText().isEmpty())
			errores.add("El campo Salario esta vacio");
		
		if(cboProfesion.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo Profesion esta vacio");
		if(cboSupervisor.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo Supervisor esta vacio");
		
		if(genero.getSelectedToggle() == null)
			errores.add("El campo Genero esta vacio");
		if(status.getSelectedToggle() == null)
			errores.add("El campo Status esta vacio");
		
	}
	
	
}
