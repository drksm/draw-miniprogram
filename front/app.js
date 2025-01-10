// app.js
App({
  globalData: {
    userInfo: null
  },

  onLaunch() {
    // 检查登录状态
    const userInfo = wx.getStorageSync('userInfo')
    if (userInfo) {
      this.globalData.userInfo = userInfo
    }
  },

  checkLogin() {
    if (!this.globalData.userInfo) {
      wx.navigateTo({
        url: '/pages/login/login'
      })
      return false
    }
    return true
  }
})
