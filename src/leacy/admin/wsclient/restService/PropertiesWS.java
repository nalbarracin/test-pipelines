package leacy.admin.wsclient.restService;

public class PropertiesWS {

	private static String urlServerRest = "http://localhost:8080/LeacyWs/rest/";
	
	public static String urlBuscarCartaPorFiltro = urlServerRest + "LeacyService/buscarCartaPorFiltro";
	
	public static String urlBuscarStockPorCartaCatalogo = urlServerRest + "LeacyService/buscarStockPorCartaCatalogo";

	public static String urlGetEdiciones = urlServerRest + "LeacyService/getEdiciones";
	
	public static String urlBuscarStockPorEdicion = urlServerRest + "LeacyService/buscarCartasStockPorEdicion";
	
	public static String urlActualizarCartaStock = urlServerRest + "LeacyService/actualizarCartaStock";
	
	public static String urlActualizarStock = urlServerRest + "LeacyService/actualizarStock";
	
	public static String urlGenerarExcelStock = urlServerRest + "LeacyService/generarExcelStock";

	public static String urlCargarCatalogoConStock = urlServerRest + "LeacyService/cargarCatalogoConStock";
	
	public static String urlCargarCatalogoConStockConVacios = urlServerRest + "LeacyService/cargarCatalogoConStockConVacios";

	public static String urlGetEdicionesConStock = urlServerRest + "LeacyService/getEdicionesConStock";

	public static String urlCargarCartasStockConStockPorEdicion = urlServerRest + "LeacyService/cargarCartasStockConStockPorEdicion";

	public static String urlCargarCartasStockConStockPorCarta = urlServerRest + "LeacyService/cargarCartasStockConStockPorCarta";
	
}
