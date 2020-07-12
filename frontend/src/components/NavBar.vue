<template>
	<header>
		<div class="logo__container">
		<router-link
            to="/home"
            aria-label="Home"> 
		<img src="store-icon.svg" alt="logo" />
			</router-link> 
		<div class="logo">
			<h4>Light Shop</h4>
		</div>
    </div>

    <nav>
		<ul class="nav__links">
        <li>
			<router-link class="nav-link" to="/home" exact aria-label="Home" itemprop="url">
				<span itemprop="name">Home</span>
			</router-link>
        </li>
        <li>
          <router-link class="nav-link" to="/about" aria-label="About" itemprop="url">
            <span itemprop="name">About</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link" to="/contact" aria-label="Contact" itemprop="url">
            <span itemprop="name">Contact</span>
          </router-link>
        </li>
      </ul>
    </nav>

    <div class="nav__right">
      <ul class="nav__links">
        <li>
			<router-link class="nav-link" to="/signup" aria-label="SignUp" itemprop="url">
				<span itemprop="name">SignUp</span>
			</router-link>
        </li>
        <li>
			<router-link class="nav-link" to="/login" aria-label="Login" itemprop="url">
				<span itemprop="name">Login</span>
			</router-link>
        </li>
      </ul>
    </div>
  </header>
</template>

<style>

.router-link-active {
  color: #c36cbb;
}

header {
  display: flex;
  width: 90%;
  height: 10vh;
  margin: auto;
  align-items: center;
}

img{
  padding: 5px;
  width: 54px;
}

.logo__container,
.nav__links,
.nav__right {
  display: flex;
}
.nav__right .nav__links li {
  padding: 0 1rem;
}

.logo__container {
  flex: 1;
}

.logo__container{
flex-direction: row;
}

.logo {

  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: 400;
  font-size: 1.5em;
  margin: 5px;
  text-align: center;
  color:#c36cbb;
}

nav {
  flex: 2;
}

.nav__links {
  justify-content: space-around;
  list-style: none;
}

.nav__link {
  color: #5f5f79;
  font-size: 18px;
  text-decoration: none;
}

.nav__right {
  flex: 1;
  justify-content: flex-end;
}

a {
  text-decoration: none;
  color: #71717e;
}

@media (max-width: 952px) {
  .logo {
    font-size: 22px;
	margin: 0;
  }

  img{
	display: none;
  }

  nav ul li a {
    font-size: 16px;
  }
}

@media (max-width: 858px) {
  .checkbtn {
    display: block;
  }

  ul {
    position: fixed;
    width: 100%;
    height: 100vh;
    background: #2c3e50;
    top: 80px;
    left: -100%;
    text-align: center;
    transition: all 0.5s;
  }

  nav ul li {
    display: block;
    margin: 50px 0;
    line-height: 30px;
  }

  nav ul li a {
    font-size: 20px;
  }

  a:hover,
  a.active {
    background: none;
    color: #0082e6;
  }

  #check:checked ~ ul {
    left: 0;
  }
}

/* section {
	background: url(image.jpeg) no-repeat;
	background-size: cover;
	height: calc(100vh - 80px);
} */
</style>