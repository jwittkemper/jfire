package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;

import biz.wittkemper.jfire.data.entity.Mitglied;

import com.jgoodies.binding.list.LinkedListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

public class MitgliedSearchModel {
	private String[] columnNames;
	
	LinkedListModel<Mitglied> mitgliedMOdel;
	ValueModel mitgliedSelectionHolder;
	SelectionInList<Mitglied> mitgliedSelectionInList;

	private AbstractTableModel tableModel;

	public MitgliedSearchModel() {
		mitgliedMOdel = new LinkedListModel<Mitglied>();
		mitgliedSelectionHolder = new ValueHolder();
		mitgliedSelectionInList = new SelectionInList<Mitglied>(
				(ListModel) mitgliedMOdel, mitgliedSelectionHolder);
		
		tableModel = new ExampleTableModel();
	}

	public void setData(List<Mitglied> liste){
		mitgliedMOdel.clear();
		for(Mitglied md: liste){
			mitgliedMOdel.add(md);
		}
		tableModel.fireTableDataChanged();
		
	}
	public AbstractTableModel getTableModel() {
		return tableModel;
	}

	public class ExampleTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		public ExampleTableModel(){
			setColumnNames();
		}
		@Override
		public int getRowCount() {
			return mitgliedSelectionInList.getSize();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public void setColumnNames(){
			columnNames = new String[] { "Vorname","Name"};
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			Mitglied md = mitgliedSelectionInList.getElementAt(rowIndex);
			switch (columnIndex) {
			case 0:
				return md.getVorname();
			case 1:
				return md.getName();
			}
			return "";
		}
	}
	public Mitglied getSelectedMitglied(int row){
		return mitgliedSelectionInList.getElementAt(row);
	}

}
