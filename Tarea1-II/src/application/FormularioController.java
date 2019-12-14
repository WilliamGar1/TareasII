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

import javax.swing.JOptionPane;

import clases.Marca;
import clases.SistemaOperativo;
import clases.Telefono;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FormularioController implements Initializable{
	
	private ObservableList<SistemaOperativo> sistemas;
	private ObservableList<Marca> marcas;
	//private ObservableList<Compania> companias;
	private ObservableList<String> modelos;
	private ObservableList<Telefono> telefonos;
	
	@FXML private ComboBox<Marca> cboMarca;
	//@FXML private ComboBox<Compania> cboCompania;
	@FXML private ComboBox<SistemaOperativo> cboSistema;
	@FXML private ComboBox<String> cboModelo;
	@FXML private TextField txtMem;
	@FXML private TextField txtDisco;
	@FXML private TextField txtResolucion;
	@FXML private TextField txtPantalla;
	@FXML private TextField txtTelefono;
	@FXML private TextField txtCamara;
	@FXML private TextField txtCpu;
	@FXML private ToggleGroup comp;
	@FXML private RadioButton rbtTigo;
	@FXML private RadioButton rbtClaro;
	@FXML private RadioButton rbtHondutel;
	@FXML private Button btnGuardar;
	@FXML private Button btnNuevo;
	@FXML private Button btnActualizar;
	@FXML private Button btnEliminar;
	@FXML private CheckBox cbNfc;
	@FXML private CheckBox cbLte;
	@FXML private CheckBox cbBlue;
	@FXML private ListView<Telefono> lstTelefonos;
	private ArrayList<String> errores;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		sistemas = FXCollections.observableArrayList();
		marcas = FXCollections.observableArrayList();
		//companias = FXCollections.observableArrayList();
		modelos = FXCollections.observableArrayList();
		telefonos = FXCollections.observableArrayList();
		errores = new ArrayList<String>();
		
		marcas.add(new Marca("Samsung", "001"));
		marcas.add(new Marca("LG", "002"));
		marcas.add(new Marca("Apple", "003"));
		marcas.add(new Marca("Sony", "004"));
		cboMarca.setItems(marcas);
		
		modelos.add("Xperia");
		modelos.add("Galaxy S10");
		modelos.add("G7");
		modelos.add("X plus");
		cboModelo.setItems(modelos);
		
		sistemas.add(new SistemaOperativo("Android", "v8.1", "2019"));
		sistemas.add(new SistemaOperativo("iOS", "v9.0", "2018"));
		sistemas.add(new SistemaOperativo("Windows", "v7.2", "2017"));
		cboSistema.setItems(sistemas);
		
		lstTelefonos.setItems(telefonos);
		
		lstTelefonos.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Telefono>() {

					@Override
					public void changed(ObservableValue<? extends Telefono> observable, Telefono valorAnterior,
							Telefono valorActual) {
						
						if (valorActual != null) {
							
							txtMem.setText(valorActual.getMemoria());
							txtDisco.setText(valorActual.getDisco());
							txtResolucion.setText(valorActual.getResolucion());
							txtPantalla.setText(valorActual.getTampan());
							txtTelefono.setText(valorActual.getNumero());
							txtMem.setText(valorActual.getMp());
							txtCpu.setText(valorActual.getCpu());
							txtCamara.setText(valorActual.getMp());
							cboMarca.getSelectionModel().select(valorActual.getMarca());
							cboModelo.getSelectionModel().select(valorActual.getModelo());
							cboSistema.getSelectionModel().select(valorActual.getOs());
							
							if (valorActual.getComp().equals("Tigo"))
								rbtTigo.setSelected(true);
							if (valorActual.getComp().equals("Claro"))
								rbtClaro.setSelected(true);
							if (valorActual.getComp().equals("Hondutel"))
								rbtHondutel.setSelected(true);
							
							
							if (valorActual.getOtros().contains(cbNfc.getText())) {
								if(valorActual.getOtros().contains(cbLte.getText())) {
									if (valorActual.getOtros().contains(cbBlue.getText())) {
										cbNfc.setSelected(true);
										cbBlue.setSelected(true);
										cbLte.setSelected(true);
									}else {
										cbNfc.setSelected(true);
										cbBlue.setSelected(false);
										cbLte.setSelected(true);
									}
								}else {
									if (valorActual.getOtros().contains(cbBlue.getText())) {
										cbNfc.setSelected(true);
										cbBlue.setSelected(true);
										cbLte.setSelected(false);
									}else {
										cbNfc.setSelected(true);
										cbBlue.setSelected(false);
										cbLte.setSelected(false);
									}
								}
							}else {
								if(valorActual.getOtros().contains(cbLte.getText())) {
									if (valorActual.getOtros().contains(cbBlue.getText())) {
										cbNfc.setSelected(false);
										cbBlue.setSelected(true);
										cbLte.setSelected(true);
									}else {
										cbNfc.setSelected(false);
										cbBlue.setSelected(false);
										cbLte.setSelected(true);
									}
								}
								
							}
													
							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
							
							
						}
						
					}
					
				}
				
				);
		leerInformacion();
		
	}
	
	
	public void salir() {
		
		JOptionPane.showMessageDialog(null, "ADIOS");
		System.exit(0);
	}
	
	@FXML
	public void guardar() {
		validar();
		
		if (errores.size()>0) {
			//Mostrar errores
			Alert mensaje = new Alert(AlertType.WARNING);
			String cadenaErrores = "";
			for (int i=0;i<errores.size();i++)
				cadenaErrores+=errores.get(i)+"\n";
			
			mensaje.setTitle("Error al guardar");
			mensaje.setContentText(cadenaErrores);
			mensaje.setHeaderText("Se encontraron los siguientes errores: ");
			mensaje.show();
			return; //Sale del metodo y no guardaria nada
		}
		
		ArrayList<String> otro = new ArrayList<String>();
		
		if(cbBlue.isSelected())
			otro.add(cbBlue.getText());
		if(cbNfc.isSelected())
			otro.add(cbNfc.getText());
		if(cbLte.isSelected())
			otro.add(cbLte.getText());
		
		
		
		Telefono telefono = new Telefono(
				cboMarca.getSelectionModel().getSelectedItem(),
				cboModelo.getSelectionModel().getSelectedItem(),
				cboSistema.getSelectionModel().getSelectedItem(),
				txtMem.getText(),
				txtDisco.getText(),
				txtResolucion.getText(),
				txtPantalla.getText(),
				((RadioButton)comp.getSelectedToggle()).getText(),
				txtTelefono.getText(),
				txtCamara.getText(),
				txtCpu.getText(),
				otro
				);
		
		
		telefonos.add(telefono);
		guardarInformacion();
		nuevo();
		
	}
	
	
	
	@FXML
	public void actualizar() {
		ArrayList<String> otro = new ArrayList<String>();
		
		if(cbBlue.isSelected())
			otro.add(cbBlue.getText());
		if(cbNfc.isSelected())
			otro.add(cbNfc.getText());
		if(cbLte.isSelected())
			otro.add(cbLte.getText());
		
		telefonos.set(
				lstTelefonos.getSelectionModel().getSelectedIndex(),
				new Telefono(
						cboMarca.getSelectionModel().getSelectedItem(),
						cboModelo.getSelectionModel().getSelectedItem(),
						cboSistema.getSelectionModel().getSelectedItem(),
						txtMem.getText(),
						txtDisco.getText(),
						txtResolucion.getText(),
						txtPantalla.getText(),
						((RadioButton)comp.getSelectedToggle()).getText(),
						txtTelefono.getText(),
						txtCamara.getText(),
						txtCpu.getText(),
						otro
						)
				);
		
	}
	
	@FXML
	public void eliminar() {
		
		telefonos.remove(lstTelefonos.getSelectionModel().getSelectedIndex());
		nuevo();
		
	}
	
	@FXML
	public void nuevo() {
		
		txtMem.clear();
		txtDisco.clear();
		txtResolucion.clear();
		txtPantalla.clear();
		txtTelefono.clear();
		txtCamara.clear();
		txtCpu.clear();
		lstTelefonos.getSelectionModel().clearSelection();
		cboMarca.getSelectionModel().clearSelection();
		cboModelo.getSelectionModel().clearSelection();
		cboSistema.getSelectionModel().clearSelection();
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
		cbBlue.setSelected(false);
		cbLte.setSelected(false);
		cbNfc.setSelected(false);
		
	}
	
	public void validar() {
		errores.clear();//borra todos los elementos de un ArrayList
			
		
		if (txtMem.getText().isEmpty())
			errores.add("El campo Capacidad de Memoria esta vacio");
		
		if (txtDisco.getText().isEmpty())
			errores.add("El campo Capacidad Disco esta vacio");
		
		if (txtResolucion.getText().isEmpty())
			errores.add("El campo Resolucion esta vacio");
		
		if (txtPantalla.getText().isEmpty())
			errores.add("El campo Tamanio Pantalla esta vacio");
		
		if (txtTelefono.getText().isEmpty())
			errores.add("El campo Numero Telefonico esta vacio");
		
		if (txtCamara.getText().isEmpty())
			errores.add("El campo Capacidad Camara esta vacio");
		
		if (txtCpu.getText().isEmpty())
			errores.add("El campo CPU esta vacio");
		
		
		if (cboModelo.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo Modelo esta vacio");
		
		if (cboMarca.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo Marca esta vacio");
		
		if (cboSistema.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo Sistema Operativo esta vacio");
		
		if (comp.getSelectedToggle()==null)
			errores.add("El campo Compania esta vacio");
	}
	
	public void leerInformacion() {
		
		try {
			ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("usuarios.data"));
			try {
				while(true) {
					Telefono a = (Telefono)archivo.readObject();
					telefonos.add(a);
				}
			}catch(EOFException e) {
				//e.printStackTrace();
				System.out.println("Fin del archivo");
			}
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarInformacion() {
		
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("usuarios.data"));
			//No se puede guardar un objeto si la clase no implementa la interfaz Serializable
			for (int i=0;i<telefonos.size();i++) {
				archivo.writeObject(telefonos.get(i));			
			}
			archivo.flush();
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
