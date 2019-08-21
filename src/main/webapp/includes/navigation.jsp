<%@page import="com.grs.user.domain.User"%>
<%@ taglib prefix="concept" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<nav id="navigation" class="navbar navbar-static-top" role="navigation">
            <div class="navbar-header">
                <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                    <i class="fa fa-reorder"></i>
                </button>
                <a href="#" class="navbar-brand" style="background-color: #8592b8">Monterrey</a>
            </div>
            <div class="navbar-collapse collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> My Profile <span class="caret"></span></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#" class="nav" nav-type="navigator" nav-value="AccountDetails.nav">Account Details</a></li>
                            <li><a href="">Preferences</a></li>
                        </ul>
                    </li>
                    
                    <concept:permision role="${ sessionScope['concept.user'].role}">
	                    <li class="dropdown">
	                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> Users <span class="caret"></span></a>
	                        <ul role="menu" class="dropdown-menu">
	                            <li><a href="#" class="nav" nav-type="navigator" nav-value="Users.nav">All Users</a></li>
	                            <li><a id="createuser" href="#" >Create</a></li>
	                        </ul>
	                    </li>
                    </concept:permision>
                    
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> Tickets <span class="caret"></span></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#" class="nav" nav-type="navigator" nav-value="Tickets.nav">All Tickets</a></li>
                            <li><a id="createticket"  href="#">Submit a Ticket</a></li>
                          </ul>
                    </li>
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> Communications <span class="caret"></span></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#" class="nav" nav-type="navigator" nav-value="Notifications.nav"> Notifications </a></li>
                            <li><a id="discussionsNav" href="#" class="nav" nav-type="navigator" nav-value="Discussions.nav"> Discussion Board </a></li>
                            <concept:permision role="${ sessionScope['concept.user'].role}">
                            <li><a  href="#" class="nav" nav-type="navigator" nav-value="DirectMessages.nav"> Direct Messages </a></li>
                            </concept:permision>
                          </ul>
                    </li>
                    
                    <concept:permision role="${ sessionScope['concept.user'].role}">
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> Units <span class="caret"></span></a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="#" class="nav" nav-type="navigator" nav-value="Units.nav">All Units</a></li>
                        </ul>
                    </li>
                    </concept:permision>
                    
                    <concept:permision role="${ sessionScope['concept.user'].role}">
                     <li class="dropdown">
                    	 <a aria-expanded="false" role="button" href="#"  data-toggle="dropdown"> Settings</a>
                     </li>
                     </concept:permision>
                     
                     <concept:admin-only role="${ sessionScope['concept.user'].role}">
                     <li class="dropdown">
                    	 <a href="#" class="nav" nav-type="navigator" nav-value="Admin.nav"> Administrator</a>
                     </li>
                     </concept:admin-only>

                </ul>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a id="logout" href="#">
                            <i class="fa fa-sign-out"></i> Log out
                        </a>
                    </li>
                </ul>
            </div>
        </nav>