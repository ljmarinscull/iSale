import { AxiosApi } from '../api/AxiosApi';

import jwt from 'jsonwebtoken';

export const authService = {
	login,
	signup,
	updateProfile
};

function login(username, password) {
	const requestOptions = {
		method: 'POST',
		headers: { 'Content-Type': 'application/json'}
	};

	return new AxiosApi()
		.post({ url: '/auth/login', body: { username, password }, options: requestOptions })
		.then((response) => {
			if (response.token) {
				const decode = jwt.verify(response.token, 'webproject', { algorithms: ['HS256']});
				const user = { user: decode.sub, name: decode.name , phone: decode.phone}
				localStorage.setItem(
						'user',
						JSON.stringify(user)
				);
				localStorage.setItem('token', response.token);
				return user
			}
		});
}

function signup(user) {

	const requestOptions = {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' }
	};

	return new AxiosApi()
		.post({ url: '/auth/signup', body: user, options: requestOptions })
}

function updateProfile(user){
	const requestOptions = {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' }
	};

	return new AxiosApi()
		.post({ url: '/user-profile', body: user, options: requestOptions })
}
