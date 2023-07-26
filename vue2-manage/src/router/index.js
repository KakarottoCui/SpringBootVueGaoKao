import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const manage = r => require.ensure([], () => r(require('@/page/manage')), 'manage');
const addShop = r => require.ensure([], () => r(require('@/page/addShop')), 'addShop');
const userList = r => require.ensure([], () => r(require('@/page/userList')), 'userList');
const shopList = r => require.ensure([], () => r(require('@/page/shopList')), 'shopList');
const foodList = r => require.ensure([], () => r(require('@/page/foodList')), 'foodList');
const orderList = r => require.ensure([], () => r(require('@/page/orderList')), 'orderList');

const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/manage',
		component: manage,
		name: '',
		children: [{
			path: '/addShop',
			component: addShop,
			meta: ['添加用户', '用户注册'],
		},{
			path: '/userList',
			component: userList,
			meta: ['志愿填报', '学校推荐'],
		},{
			path: '/shopList',
			component: shopList,
			meta: ['志愿填报', '专业推荐'],
		},{
			path: '/foodList',
			component: foodList,
			meta: ['志愿填报', '职业测试'],
		},{
			path: '/orderList',
			component: orderList,
			meta: ['志愿填报', '志愿评估'],
		}]
	}
]

export default new Router({
	routes,
	strict: process.env.NODE_ENV !== 'production',
})
