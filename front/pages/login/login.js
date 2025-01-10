import config from '../../config'

Page({
  data: {
    loading: false
  },

  handleLogin() {
    this.setData({ loading: true })
    
    wx.login({
      success: (res) => {
        console.log('wx.login success:', res)
        if (res.code) {
          wx.request({
            url: `${config.baseUrl}/api/wx/login`,
            method: 'POST',
            header: {
              'content-type': 'application/json'
            },
            data: {
              code: res.code,
              nickName: '微信用户',  // 默认昵称
              avatarUrl: ''  // 默认头像
            },
            success: (result) => {
              console.log('登录接口返回:', result)
              if (result.data.code === 0) {
                wx.setStorageSync('userInfo', result.data.data)
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 2000,
                  success: () => {
                    setTimeout(() => {
                      wx.switchTab({
                        url: '/pages/index/index'
                      })
                    }, 2000)
                  }
                })
              } else {
                wx.showToast({
                  title: result.data.msg || '登录失败',
                  icon: 'none'
                })
              }
            },
            fail: (error) => {
              console.error('登录接口错误:', error)
              wx.showToast({
                title: '网络错误',
                icon: 'none'
              })
            },
            complete: () => {
              this.setData({ loading: false })
            }
          })
        }
      },
      fail: (error) => {
        console.error('wx.login fail:', error)
        this.setData({ loading: false })
        wx.showToast({
          title: '登录失败',
          icon: 'none'
        })
      }
    })
  }
}) 