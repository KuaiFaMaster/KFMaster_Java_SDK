# KFMaster_Java_SDK
快发助手AndroidSDK


# SDK版本3.0升级至3.1.0说明 #
1. 新增生命周期函数onActivityResult ，必接
2. 统计进入游戏接口(sdkObj.Collections.onDatas(DataTypes.DATA_ENTER_GAME, pc))新增参数ParamsKey.KEY_ROLE_CREATETIME、ParamsKey.KEY_ROLE_UPGRADETIME 具体传值说明请参见demo
3. 统计角色升级接口(sdkObj.Collections.onDatas(DataTypes.DATA_ROLE_UPGRADE, pc))新增参数ParamsKey.KEY_ROLE_CREATETIME、ParamsKey.KEY_ROLE_UPGRADETIME 具体传值说明请参见demo