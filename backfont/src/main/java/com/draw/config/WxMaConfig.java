// 暂时注释掉整个类，因为测试环境不需要微信配置
/*
@Configuration
public class WxMaConfig {
    @Value("${wx.miniapp.appid}")
    private String appid;
    
    @Value("${wx.miniapp.secret}")
    private String secret;
    
    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appid);
        config.setSecret(secret);
        
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}
*/ 