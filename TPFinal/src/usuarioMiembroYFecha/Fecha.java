package usuarioMiembroYFecha;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	
	Date fecha;
	
	public Fecha()
	{
			
		this.fecha=new Date();
		
	}
	
	public Fecha(String yyyyMMdd)
	{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = yyyyMMdd;
		this.fecha=null;
		try {

			this.fecha = formatoDelTexto.parse(strFecha);
            
		} catch (ParseException ex) {

		ex.printStackTrace();

		}}
		
		protected boolean antesQue(Fecha cuando)
		{
			return this.getFecha().before(cuando.getFecha());
			
		}
		
		protected boolean DespuesQue(Fecha cuando)
		{
			return this.getFecha().after(cuando.getFecha());
			
		}
		
	
		public Fecha fechaDeHoy()
		{
			
			return new Fecha();
		}

	
	
	public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

}
