import { AxiosApi } from '../api/AxiosApi';
import { authHeader } from '../helpers/auth-header';

export const userService = {
	updateUserProfile,
};

const patchReqOpt = {
	method: 'PATCH',
	headers: authHeader()
};

async function updateUserProfile(user) {
	const newUser = await new AxiosApi().patch({ url: '/user-profile', body: user, options: patchReqOpt });
	return newUser
}