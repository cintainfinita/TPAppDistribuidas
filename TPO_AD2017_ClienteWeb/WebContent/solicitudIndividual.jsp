<%@page import="interfazRemota.*"%>
<%@page import="bd.*"%>
<%@page import="dto.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Solicitud Individual</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>

<form action="/TPO_AD2017_ClienteWeb/Controller">

   Elija Materia Prima a Agregar y su Cantidad<br><br>
    
<%

List<MateriaPrimaDTO> mps = (List<MateriaPrimaDTO>)request.getAttribute("mps");
%>

    </td></tr>
 <br>

 <Select name = "codMP">
<% for(MateriaPrimaDTO mp :  mps) {%>
	<option value= <%= mp.getCodigo() %>><%= mp.getDescripcion() %></option> 
<%} %>
</select>
Cantidad</td><td><input name="cantidad", size="20"/></td></tr>
Responsable</td><td><input name="responsable", size="20"/></td></tr>
Motivo</td><td><input name="motivo", size="20"/></td></tr>
<tr><td colspan="2" align="right"><input name="boton" type="submit" value="Aceptar"  /></td></tr>

<input type="hidden" name="opcion" value="agregarSolicitudIndividual">

</form>


</body>
</html>