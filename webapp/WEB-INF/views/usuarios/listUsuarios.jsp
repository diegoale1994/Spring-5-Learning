<%@taglib uri ="http://www.springframework.org/tags" prefix = "spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de Usuarios</title>
    <spring:url value="/resources" var="urlPublic" />
    <spring:url value="/usuarios/create" var="urlCreate" />
     <spring:url value="/noticias" var="urlNoticias" />
    <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

    <!-- Fixed navbar -->
    <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3>Listado de Usuarios</h3>
      <c:if test="${mensaje !=null}">
			<div class='alert alert-success' role='alert'>${mensaje}</div>
		</c:if>
      <a href="${urlCreate }" class="btn btn-success" role="button" title="Nuevo Usuario" >Nuevo</a><br><br>

      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Email</th>              
                <th>Username</th>              
                <th>Activo</th>    
                <th>Acciones</th>             
            </tr>
            <c:forEach items="${usuarios}" var="usuario">
            
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre }</td>
                <td>${usuario.email}</td>              
                <td>${usuario.username}</td> 
                <c:choose>
							<c:when test="${usuario.activo eq 1}">
								<td><span class="label label-success">Activo</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="label label-danger">Inactivo</span></td>
							</c:otherwise>
						</c:choose>
                
          
                <td>
                <spring:url value="/usuarios/edit/${usuario.id}" var="urlEditNoticia" />
                 <spring:url value="/usuarios/delete/${usuario.id}" var="urlDeleteNoticia" />
                    <a href="${urlEditNoticia }" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${urlDeleteNoticia }" class="btn btn-danger btn-sm" role="button" onclick ='return confirm("Esta seguro?")' title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            
            </c:forEach>
            
        </table>
      </div>  
      <hr class="featurette-divider">

      <!-- FOOTER -->
           <jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>     
  </body>
</html>
