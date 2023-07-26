import fetch from '@/config/fetch'

/**
 * 登陆
 */

export const login = data => fetch('/Info/login', data, 'POST');

/**
 * 退出
 */

export const signout = () => fetch('/admin/signout');

/**
 * 注册
 */
export const register = data => fetch('/Info/register', data, 'POST');

export const schoolByScore = data => fetch('/Info/school/queryList', data, 'POST');

/**
 * 获取用户信息
 */

export const getAdminInfo = () => fetch('/admin/info');

/**
 * api请求量
 */

export const apiCount = date => fetch('/statis/api/' + date + '/count');

/**
 * 所有api请求量
 */

export const apiAllCount = () => fetch('/statis/api/count');


/**
 * 所有api请求信息
 */

export const apiAllRecord = () => fetch('/statis/api/all');


