import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Address Book Application
 * This class is a basic address book GUI
 * 
 * @author Ben Hughey
 * @version 2.00,
 * @since 26 Mar 2019
 */

public class AddressBook extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField searchField;
	public JTable table;
	String[] fieldNames = {
			"firstName", "lastName", "mobile", "homeph", "address"
	};
	
	//ArrayList of text fields
	private ArrayList<JTextField> fieldList = new ArrayList<JTextField>();
	//Array used to gather field input
	String [] fieldsArr = new String[5];
	
	/**
	 * Add to table Method
	 * 
	 * Takes row to add as input and adds it to the specified table
	 * 
	 * @param tbl
	 * @param rowToAdd
	 * @since version 1.00
	 */
	 public void addtoTable(JTable tbl, String[] rowToAdd) {
			DefaultTableModel model = (DefaultTableModel) tbl.getModel();
			model.addRow(rowToAdd);
	   }
	 /**
	  * Delete row Method
	  * Removes selected row from specified table
	  * 
	  * @param tbl
	  * @since version 1.00
	  */
	 	public void deleteRow(JTable tbl) {
		
	 			if (tbl.getSelectedRows().length >0 ) {
	 					DefaultTableModel model = (DefaultTableModel) tbl.getModel();
	 					model.removeRow(tbl.getSelectedRow());
	 			}
	}
	/**
	 * Main Method
	 * 
	 * @param args
	 * @since version 1.00
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressBook window = new AddressBook();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 /**
	  * Create The application
	  */
	public AddressBook() {
		initialize();
	}

	/**
	 * Initialize Method
	 * 
	 * Initializes all the contents of the frame
	 * @since version 1.00
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 370, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddressBook = new JLabel("Address Book");
		lblAddressBook.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddressBook.setBounds(105, 49, 139, 45);
		frame.getContentPane().add(lblAddressBook);
		
		 //textfield for search
		searchField = new JTextField();
		searchField.setBounds(23, 118, 221, 36);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
		btnSearch.setBounds(254, 117, 89, 37);
		frame.getContentPane().add(btnSearch);
		
		/**
		 * Search entries event listener
		 * Searches table entries for relevant text when search button clicked
		 * 
		 * @since version 1.00
		 */
		btnSearch.addActionListener(new ActionListener(){
			String searchTitle;
	
			               public void actionPerformed(ActionEvent event){
		                       searchTitle = searchField.getText().toString();
		                       System.out.println(searchTitle);
		                       
		                       TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		                    
		                       table.setRowSorter(sorter);
		                       sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTitle));
		                   	
			               }
		});
		
		JButton btnAddNew = new JButton("Add New");
		
		
		/**
		 * 
		 * Clears text fields for new entry when button clicked
		 * @Since version 1.00
		 */
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					table.getSelectionModel().clearSelection();
					  for (JTextField field : fieldList) {
					    field.setText("");
					  }
			}
		});

		btnAddNew.setFont(new Font("Arial", Font.BOLD, 12));
		btnAddNew.setBounds(23, 206, 89, 36);
		frame.getContentPane().add(btnAddNew);
		
		/**
		 * Creates table for contacts
		 * @Since Version 1.00
		 */
		
		table = new JTable() {
	      
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {      
	        	
                return false;               
        };
        
		};
		
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setBorder(null);
		table.setFont(new Font("Arial", Font.BOLD, 16));
		table.setRowSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Aaron", "Adams", "12345678", "4534537567", "123 Fake st"},
				{"Barry", "Beaumont", "1235454656", "354346464", "4 Piccadilly st"},
				{"Charlie", "Carlson", "75765675", "324324324", "2 George st"},
				{"Dianne", "Smith", "453345354", "43244564", "14 Ossian st"},
				{"Edward", "Johnson", "34535434", "5345353453", "12 Mayfair Pde"},
				{"Filia", "Phillips", "686786876", "3453453455", "20 Sunset Blvd"},
				{null, null, null, null, null},
			},
			new String[] {
				"First Name", "Last Name", "Mobile", "Home Phone", "Address"
			}
		));
		
		//Sets table widths
		table.getColumnModel().getColumn(0).setMinWidth(35);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(3).setMaxWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setMaxWidth(15);

		// add table to scroll panel
	    JScrollPane scroll_table = new JScrollPane(table);            
	    scroll_table.setBounds(10, 272, 340, 112);
	    scroll_table.setVisible(true);
	    frame.getContentPane().add(scroll_table);
		
	    
	    /**
	     * Row selection event listener 
	     * gets row data and puts it in appropriate text fields when selected
	     * @since version 1.00
	     */
	    table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			
			try {
				 ArrayList<String> allTextFields = new ArrayList<String>();
				
				 for (int x = 0; x < table.getColumnCount(); x++) {
					 String value = table.getModel().getValueAt(row, x).toString();
					 fieldsArr[x] = value;
					 allTextFields.add(value);
					 System.out.println(value);
					 //Fills in Text Fields
					 fieldList.get(x).setText(value);
				 }
			}
			catch(Exception ex) {
				System.out.println(ex);
				}
		}	
	});
	    
		//Button for deleting entries
		JButton btnDeleteSelected = new JButton("Delete");
		
		/**
		 * Delete entry event listener 
		 * Removes selected entry from table when button clicked
		 * 
		 * @Since version 1.00
		 */
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (table.getSelectedRows().length >0 ) {
				
				int answer = JOptionPane.showConfirmDialog(null,
						"Delete Contact", "Are you sure you want to delete contact?", JOptionPane.YES_NO_OPTION);
				if(answer ==JOptionPane.YES_OPTION){  
					deleteRow(table);
				}
			}
			}
		});
		btnDeleteSelected.setFont(new Font("Arial", Font.BOLD, 12));
		btnDeleteSelected.setBounds(133, 206, 89, 36);
		frame.getContentPane().add(btnDeleteSelected);
		
		//Creates labels for text fields
		
		JLabel label = new JLabel("First Name :");
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(33, 401, 123, 24);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Last Name :");
		label_1.setFont(new Font("Arial", Font.BOLD, 18));
		label_1.setBounds(33, 436, 123, 24);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Mobile :");
		label_2.setFont(new Font("Arial", Font.BOLD, 18));
		label_2.setBounds(65, 472, 74, 24);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Home Ph :");
		label_3.setFont(new Font("Arial", Font.BOLD, 18));
		label_3.setBounds(42, 508, 97, 24);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Address :");
		label_4.setFont(new Font("Arial", Font.BOLD, 18));
		label_4.setBounds(23, 543, 97, 24);
		frame.getContentPane().add(label_4);
		
	
		/**
		 * Save button event listener
		 * Saves new entries and changes when clicked
		 * 
		 * @Since version 1.00
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Checks that at least a name is given
				if (fieldList.get(1).getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null,
							"Empty Fields", "Blank entries are not allowed, please at least provide a name", JOptionPane.OK_OPTION);
				}
				else {
					deleteRow(table);
					
					for(int i = 0; i <fieldList.size(); i++) {
						fieldsArr[i] = fieldList.get(i).getText();
					}
					  for (JTextField field : fieldList) {
						  	
						    field.setText("");
						  }
					  
					  //Adds new Row to table
					  DefaultTableModel model = (DefaultTableModel) table.getModel();
					  model.addRow(fieldsArr);
				}
			}
		});
		
		
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setBounds(241, 206, 89, 36);
		frame.getContentPane().add(btnSave);
		
		//Creates Arraylist of Text Fields
		for (int i = 0; i < fieldNames.length; i++) {
			  JTextField field = new JTextField();
			  fieldList.add(field);
			  frame.getContentPane().add(field); // some JPanel that holds the text fields
			  fieldList.get(i).setColumns(11);
			}
		
		//First Name text field
		fieldList.get(0).setBounds(163, 399, 136, 26);
		//Last Name Text Field
		fieldList.get(1).setBounds(163, 436, 136, 26);
		//Mobile Text Field
		fieldList.get(2).setBounds(163, 472, 136, 26);
		//Home phone text field
		fieldList.get(3).setBounds(163, 506, 136, 26);
		//Address text field
		fieldList.get(4).setBounds(118, 543, 202, 26);
	}
}