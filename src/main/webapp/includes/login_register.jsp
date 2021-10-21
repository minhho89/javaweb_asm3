				<!--  Login & Register -->
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<p class="text-light">Hello ${sessionScope.account.name} !</p>
						<a class="m-1 text-light"
							href="${pageContext.request.contextPath}/logout">Log out</a>
					</c:when>
					<c:otherwise>
						<a class="m-1 text-light"
							href="${pageContext.request.contextPath}/login.jsp">Login</a>
						<a class="m-1 text-light" href="#">Register</a>
					</c:otherwise>
				</c:choose>
				</div>
		</nav>
	</div>
</header>
<!--  End Header -->
				