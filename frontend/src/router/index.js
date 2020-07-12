import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

// Create a new router
const router = new Router({
	mode: 'history',
	routes: [
		{ path: '/', redirect: '/home' },
		{
			path: '/home',
			name: 'home',
			component: () => import(`@/views/Home.vue`)
		},
		{
			path: '/contact',
			name: 'contact',
			component: () => import(`@/views/Contact.vue`)
		},
		{
			path: '/about',
			name: 'about',
			component: () => import(`@/views/About.vue`)
		},
		{
			path: '/product-details/:id',
			name: 'product-details',
			component: () => import(`@/views/ProductDetails.vue`)
		},
		{
			path: '/login',
			name: 'login',
			component: () => import(`@/views/Login.vue`)
		},
		{
			path: '/signup',
			name: 'signup',
			component: () => import(`@/views/SignUp.vue`)
		},
		{
			path: '/error',
			name: 'error',
			component: () => import(`@/views/Error.vue`)
		},		
		{
			path: '/public-error',
			name: 'public-error',
			component: () => import(`@/views/PublicError.vue`)
		},
		{
			path: '/app',
			name: 'Layout',
			component: () => import(`@/views/Layout.vue`),
			children: [
				{
					path: 'dashboard',
					name: 'Dashboard',
					component: () => import(`@/views/Dashboard.vue`)
				},
				{
					path: 'profile',
					name: 'profile',
					component: () => import(`@/views/Profile.vue`)
				},
				{
					path: 'create-product',
					name: 'create-product',
					component: () => import(`@/views/CreateProduct.vue`)
				},
				{
					path: 'edit-product/:id',
					name: 'edit-product',
					component: () => import(`@/views/EditProduct.vue`)
				}
			]
		},
		{ path: '*', redirect: '/error' }
	],
	scrollBehavior(to, from, savedPosition) {
		if (savedPosition) {
			return savedPosition;
		}
		if (to.hash) {
			return { selector: to.hash };
		}
		return { x: 0, y: 0 };
	}
});

router.beforeEach((to, from, next) => {
	// redirect to login page if not logged in and trying to access a restricted page
	const publicPages = [ '/home', '/about', '/contact',  '/product-details', '/login', '/signup','/public-error' ];

	const authRequired = !publicPages.includes(to.path) && !to.path.includes('/product-details') ;
	const loggedIn = localStorage.getItem('user');

	if (authRequired && !loggedIn) {
		return next('/login');
	} else if (!authRequired && loggedIn) {
		next({ path: '/app/dashboard' });
	} else {
		next();
	}
});

export default router;
