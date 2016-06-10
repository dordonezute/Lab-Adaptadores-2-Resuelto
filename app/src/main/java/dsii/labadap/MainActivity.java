package dsii.labadap;

import java.io.IOException;
import java.io.InputStream;

import dsii.labadap.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	public static final int NUM_ITEMS = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView lv = (ListView) findViewById(R.id.lv);

		Celda[] elementos = llenaCeldas();
		
		ImgAdapter adapter = new ImgAdapter(this, R.layout.imgview_item, elementos);
		
		lv.setAdapter(adapter);
		
		//TODO:
		//Defina en el ListView un Listener de manera que al dar click en cualquiera de sus
		//items, se presente un mensaje Toast con el nombre de la fruta (inglés y español)
		//que se seleccionó
		
		lv.setOnItemClickListener(listItemClick);
	}
	
	//Listener del punto anterior		
	private OnItemClickListener listItemClick = new OnItemClickListener() {
	    public void onItemClick(AdapterView parent, View v, int position, long id) {
	    	Celda celda = (Celda) parent.getAdapter().getItem(position);
	    	String str = celda.espanol + " es " + celda.ingles;
	    	
	        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
	    }
	};		
	 
	private Celda[] llenaCeldas() {
		Celda[] elementos = new Celda[NUM_ITEMS];
		//TODO:
		//Llene el arreglo Celda[] con las imágenes y nombres de las frutas (inglés y español)
		elementos[0] = new Celda(cargaBmpDesdeAssets("apple.png"), "Manzana", "Apple");
		elementos[1] = new Celda(cargaBmpDesdeAssets("banana.png"), "Plátano", "Banana");
		elementos[2] = new Celda(cargaBmpDesdeAssets("grape.png"), "Uva", "Grape");
		elementos[3] = new Celda(cargaBmpDesdeAssets("orange.png"), "Naranja", "Orange");
		elementos[4] = new Celda(cargaBmpDesdeAssets("papaya.png"), "Papaya", "Papaya");
		elementos[5] = new Celda(cargaBmpDesdeAssets("pineapple.png"), "Piña", "Pineapple");
		elementos[6] = new Celda(cargaBmpDesdeAssets("strawberry.png"), "Frutilla", "Strawberry");
		elementos[7] = new Celda(cargaBmpDesdeAssets("watermelon.png"), "Sandía", "Watermelon");
		return elementos;
	}
	
	
	private Bitmap cargaBmpDesdeAssets(String nombreArchivo) {
		try {
			InputStream inputStream = getAssets().open(nombreArchivo);
			Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
			return bitmap;			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private class Celda {
		Bitmap bmp;
		String espanol;
		String ingles;
		
		Celda(Bitmap bmp, String espanol, String ingles) {
			this.bmp = bmp;
			this.espanol = espanol;
			this.ingles = ingles;
		}
	}
	
	private class ImgAdapter extends ArrayAdapter<Celda> {

		private int resource;
		private Celda[] objects;
		
		public ImgAdapter(Context context, int resource, Celda[] objects) {
			super(context, resource, objects);
			this.resource = resource;
			this.objects = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//TODO:
			//Complete este método para que devuelva los elementos de la lista, los
			//cuales deben contener la imagen y el nombre(inglés y español) de la fruta
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(resource, null);
			}

			ImageView img = (ImageView) convertView.findViewById(R.id.iv);
			img.setImageBitmap(objects[position].bmp);
			TextView tv = (TextView) convertView.findViewById(R.id.tvEspanol);
			tv.setText(objects[position].espanol);
			tv = (TextView) convertView.findViewById(R.id.tvIngles);
			tv.setText(objects[position].ingles);			
			return convertView;
		}
		
	}	
	
}
