package com.yido.clubd.component;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CustomDataAdapter implements JRDataSource {

	private JSONObject json = null;
	private boolean flag = false;
	
	public CustomDataAdapter(JSONObject json) {
		this.json = json;
	}
	
	/**
     * Return an instance of the class that implements the custom data adapter.
     */
    public static JRDataSource getDataSource(JSONObject json) {
    	CustomDataAdapter adapter = new CustomDataAdapter(json);
    	//adapter.setJson(json);
        return adapter;
    }
    
    private void setJson(JSONObject json) {
    	this.json = json;
    }
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		try {
            return this.json.get(field.getName());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean next() throws JRException {
		if (this.json != null && !flag) {
            flag = true;
            return true;
        } else {
            return false;
        }
	}

}
