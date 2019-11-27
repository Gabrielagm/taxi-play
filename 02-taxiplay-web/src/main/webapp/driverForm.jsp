<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--//<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Formulario nuevo</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div class="container">
    <h1>Taxiplay</h1>
    <h2>Ingresa los datos</h2>
    <br>
    <div align="center">

        <c:if test="${driver != null}">
        <form action="update" method="post">
            </c:if>
            <c:if test="${driver == null}">
            <form action="insert" method="post">
                </c:if>
                <%--                <table border="1" cellpadding="5">--%>
                <table id="tablaNuevo" class="table table-bordered table-striped table-hover">

                    <c:if test="${driver != null}">
                        <input type="hidden" name="id" value="<c:out value='${driver.id}' />" />
                    </c:if>
                    <tr>
                        <th>Nombres: </th>
                        <td>
                            <input type="text" name="nombres"
                                   value="<c:out value='${driver.nombres}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Apellidos: </th>
                        <td>
                            <input type="text" name="apellidos"
                                   value="<c:out value='${driver.apellidos}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>DNI: </th>
                        <td>
                            <input type="text" name="dni"
                                   value="<c:out value='${driver.dni}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Teléfono: </th>
                        <td>
                            <input type="text" name="telefono"
                                   value="<c:out value='${driver.telefono}' />"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Correo: </th>
                        <td>
                            <input type="text" name="correo"
                                   value="<c:out value='${driver.correo}' />"
                            />
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <input class="btn btn-primary" type="submit" value="Guardar" />
                        </td>
                        <%--                    <td colspan="2" align="center">--%>
                        <%--                        <input type="submit" value="Save" />--%>
                        <%--                    </td>--%>
                    </tr>
                </table>
            </form>
    </div>
    <h3 align="center">
        <a href="new">Add New Driver</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Drivers</a>

    </h3>
</div>
</body>
</html>

