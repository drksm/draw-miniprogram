// index.js
const app = getApp()

Page({
  data: {
    userInfo: null,
    loginTime: ''
  },

  onLoad() {
    const userInfo = wx.getStorageSync('userInfo')
    if (userInfo) {
      this.setData({
        userInfo: userInfo,
        loginTime: new Date().toLocaleString()
      })
      
      wx.showToast({
        title: '登录成功',
        icon: 'success',
        duration: 2000
      })
    } else {
      wx.redirectTo({
        url: '/pages/login/login'
      })
    }
  },

  onShow() {
    // 检查登录状态
    if (!app.checkLogin()) {
      return
    }
  }
})
