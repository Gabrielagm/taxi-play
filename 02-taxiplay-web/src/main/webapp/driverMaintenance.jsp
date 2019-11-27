
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nuestros choferes</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div class="container">
    <h1>Taxiplay</h1>
    <br>
    <h2>Nuestros Choferes</h2>
    <div class="table-responsive">

        <table id="tablaChoferes" class="table table-bordered table-striped table-hover">
            <head>
                <tr>
                    <th>Id</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>DNI</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                </tr>
            </head>
            <tbody>
            <c:forEach var="driver" items="${driverList}">
                <tr>
                    <td><c:out value="${driver.id}" /></td>
                    <td><c:out value="${driver.nombres}" /></td>
                    <td><c:out value="${driver.apellidos}" /></td>
                    <td><c:out value="${driver.dni}" /></td>
                    <td><c:out value="${driver.telefono}" /></td>
                    <td><c:out value="${driver.correo}" /></td>
                    <td>
                        <a class="btn btn-primary" href="edit?id=<c:out value='${driver.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-primary" href="delete?id=<c:out value='${driver.id}' />">Borrar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="button">
        <a class="btn btn-primary" href="new" role="button">Nuevo Ingreso</a>
    </div>


</div>

</body>
</html>
