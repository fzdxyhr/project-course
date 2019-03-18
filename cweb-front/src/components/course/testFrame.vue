<template>
	<div>
		<iframe id="iframe" src="http://localhost:8888/home/backupConfigDialog"></iframe>
		<!-- <iframe id="iframe" src="http://localhost:8081/#/ClassManage" ></iframe> -->
		<el-button type="primary" @click="send">发送消息</el-button>
	</div>
</template>

<script>
	export default {
		mounted() {

			console.log("fffffffffffffffffffffffff")
		},
		data() {
			return {
				data: {
					"initType": "new",
					"devices": [{
						"id": 2913,
						"deviceId": "a3b572f6-0dc6-4c8f-a964-9b4b5fb31365",
						"deviceName": "S57H",
						"deviceIp": "172.18.165.33",
						"deviceVtepIp": null,
						"deviceType": "switch",
						"deviceOnline": true,
						"deviceMac": "58:69:6c:71:da:90",
						"devicePhyarea": null,
						"deviceLocation": null,
						"deviceManufactor": "RUIJIE",
						"deviceModel": "S5750C-48GT4XS-H",
						"deviceRole": "sagg",
						"deviceNetRole": "aggregate",
						"deviceSoftwareVersion": "RGOS 11.4(1)B28",
						"deviceHardwareVersion": "1.01",
						"deviceSn": "G1K30BY000792",
						"deviceUpTime": "2019-03-14 20:52:28",
						"deviceInputConfig": null,
						"deviceProvision": "部署成功",
						"createDate": "2019-03-18 14:11:30",
						"modifyDate": "2019-03-18 17:03:25",
						"zones": [{
							"id": 2851,
							"oncZoneId": null,
							"oncZoneName": null,
							"ibnsZoneId": 1402,
							"ibnsZoneName": "1402",
							"ibnsZoneIds": "1/1402",
							"deploy": false
						}],
						"protocolConfigurations": [{
							"id": 3557,
							"name": "snmpv2",
							"certificateId": 1452,
							"snmpV2": {
								"id": 3063,
								"port": "161",
								"community": "public",
								"online": true
							}
						}, {
							"id": 3558,
							"name": "ssh",
							"certificateId": 1453,
							"ssh": {
								"id": 3113,
								"user": "ruijie",
								"password": "ruijie",
								"port": "22",
								"needsupwd": true,
								"protocolType": "SSH",
								"enableCommand": "enable",
								"enablePassword": "ruijie",
								"online": true
							}
						}, {
							"id": 3559,
							"name": "telnet",
							"certificateId": 1451,
							"telnet": {
								"id": 3163,
								"authMode": "USER_PASSWORD",
								"user": "ruijie",
								"password": "ruijie",
								"timeout": 0,
								"needsupwd": true,
								"port": 23,
								"suprompt": "Password:",
								"prompt": ">",
								"superPassword": "ruijie",
								"online": false
							}
						}, {
							"id": 3560,
							"name": "netconf",
							"certificateId": 1454,
							"netconf": {
								"id": 3211,
								"user": "ruijie",
								"password": "ruijie",
								"port": "830",
								"useTcpOnly": false,
								"online": true,
								"version": "1.1"
							}
						}, {
							"id": 3561,
							"name": "openflow",
							"openflow": {
								"id": 3261,
								"oid": "97209814211216",
								"online": true,
								"version": "OFP_VERSION_1_3"
							}
						}],
						"zonesValue": "默认区域-zone_1",
						"area_name": "默认区域-zone_1",
						"location": null,
						"device_ip": "172.18.165.33",
						"device_model": "S5750C-48GT4XS-H"
					}, {
						"id": 2916,
						"deviceId": "a9c3a42d-6e98-4197-8412-b665f13f9bad",
						"deviceName": "S29XS-1",
						"deviceIp": "172.18.165.34",
						"deviceVtepIp": null,
						"deviceType": "switch",
						"deviceOnline": true,
						"deviceMac": "00:74:9c:05:d8:70",
						"devicePhyarea": null,
						"deviceLocation": null,
						"deviceManufactor": "RUIJIE",
						"deviceModel": "S2910-48GT4XS-E",
						"deviceRole": "sacc",
						"deviceNetRole": "access",
						"deviceSoftwareVersion": "RGOS 11.4(1)B24",
						"deviceHardwareVersion": "1.11",
						"deviceSn": "G1LQ6JB007894",
						"deviceUpTime": "2019-03-14 20:52:03",
						"deviceInputConfig": null,
						"deviceProvision": "部署成功",
						"createDate": "2019-03-18 14:11:31",
						"modifyDate": "2019-03-18 17:03:25",
						"zones": [{
							"id": 2851,
							"oncZoneId": null,
							"oncZoneName": null,
							"ibnsZoneId": 1402,
							"ibnsZoneName": "1402",
							"ibnsZoneIds": "1/1402",
							"deploy": false
						}],
						"protocolConfigurations": [{
							"id": 3572,
							"name": "snmpv2",
							"certificateId": 1452,
							"snmpV2": {
								"id": 3066,
								"port": "161",
								"community": "public",
								"online": true
							}
						}, {
							"id": 3573,
							"name": "ssh",
							"certificateId": 1453,
							"ssh": {
								"id": 3116,
								"user": "ruijie",
								"password": "ruijie",
								"port": "22",
								"needsupwd": true,
								"protocolType": "SSH",
								"enableCommand": "enable",
								"enablePassword": "ruijie",
								"online": true
							}
						}, {
							"id": 3574,
							"name": "telnet",
							"certificateId": 1451,
							"telnet": {
								"id": 3166,
								"authMode": "USER_PASSWORD",
								"user": "ruijie",
								"password": "ruijie",
								"timeout": 0,
								"needsupwd": true,
								"port": 23,
								"suprompt": "Password:",
								"prompt": ">",
								"superPassword": "ruijie",
								"online": false
							}
						}, {
							"id": 3575,
							"name": "netconf",
							"certificateId": 1454,
							"netconf": {
								"id": 3214,
								"user": "ruijie",
								"password": "ruijie",
								"port": "830",
								"useTcpOnly": false,
								"online": true,
								"version": "1.1"
							}
						}, {
							"id": 3576,
							"name": "openflow",
							"openflow": {
								"id": 3264,
								"oid": "500833835120",
								"online": true,
								"version": "OFP_VERSION_1_3"
							}
						}],
						"zonesValue": "默认区域-zone_1",
						"area_name": "默认区域-zone_1",
						"location": null,
						"device_ip": "172.18.165.34",
						"device_model": "S2910-48GT4XS-E"
					}, {
						"id": 2914,
						"deviceId": "36611e6b-26a1-4b73-9617-696a33c6c23f",
						"deviceName": "S8600E",
						"deviceIp": "192.168.31.86",
						"deviceVtepIp": null,
						"deviceType": "switch",
						"deviceOnline": true,
						"deviceMac": "00:d0:f8:00:11:72",
						"devicePhyarea": null,
						"deviceLocation": null,
						"deviceManufactor": "RUIJIE",
						"deviceModel": "S8605E",
						"deviceRole": "sgw",
						"deviceNetRole": "core",
						"deviceSoftwareVersion": "RGOS 11.0(4)B55",
						"deviceHardwareVersion": "1.00",
						"deviceSn": "1234942570012",
						"deviceUpTime": "2019-03-14 20:49:48",
						"deviceInputConfig": null,
						"deviceProvision": "部署成功",
						"createDate": "2019-03-18 14:11:31",
						"modifyDate": "2019-03-18 17:53:25",
						"zones": [{
							"id": 2851,
							"oncZoneId": null,
							"oncZoneName": null,
							"ibnsZoneId": 1402,
							"ibnsZoneName": "1402",
							"ibnsZoneIds": "1/1402",
							"deploy": false
						}],
						"protocolConfigurations": [{
							"id": 3562,
							"name": "snmpv2",
							"certificateId": 1452,
							"snmpV2": {
								"id": 3064,
								"port": "161",
								"community": "public",
								"online": true
							}
						}, {
							"id": 3563,
							"name": "ssh",
							"certificateId": 1453,
							"ssh": {
								"id": 3114,
								"user": "ruijie",
								"password": "ruijie",
								"port": "22",
								"needsupwd": true,
								"protocolType": "SSH",
								"enableCommand": "enable",
								"enablePassword": "ruijie",
								"online": true
							}
						}, {
							"id": 3564,
							"name": "telnet",
							"certificateId": 1451,
							"telnet": {
								"id": 3164,
								"authMode": "USER_PASSWORD",
								"user": "ruijie",
								"password": "ruijie",
								"timeout": 0,
								"needsupwd": true,
								"port": 23,
								"suprompt": "Password:",
								"prompt": ">",
								"superPassword": "ruijie",
								"online": false
							}
						}, {
							"id": 3565,
							"name": "netconf",
							"certificateId": 1454,
							"netconf": {
								"id": 3212,
								"user": "ruijie",
								"password": "ruijie",
								"port": "830",
								"useTcpOnly": false,
								"online": true,
								"version": "1.1"
							}
						}, {
							"id": 3566,
							"name": "openflow",
							"openflow": {
								"id": 3262,
								"oid": "897513951602",
								"online": true,
								"version": "OFP_VERSION_1_3"
							}
						}],
						"zonesValue": "默认区域-zone_1",
						"area_name": "默认区域-zone_1",
						"location": null,
						"device_ip": "192.168.31.86",
						"device_model": "S8605E"
					}, {
						"id": 2915,
						"deviceId": "a0403ceb-b95e-4522-ba8c-9e9f5a35c647",
						"deviceName": "S29XS-2",
						"deviceIp": "172.18.165.35",
						"deviceVtepIp": null,
						"deviceType": "switch",
						"deviceOnline": true,
						"deviceMac": "00:74:9c:05:d7:34",
						"devicePhyarea": null,
						"deviceLocation": null,
						"deviceManufactor": "RUIJIE",
						"deviceModel": "S2910-48GT4XS-E",
						"deviceRole": "sacc",
						"deviceNetRole": "access",
						"deviceSoftwareVersion": "RGOS 11.4(1)B24",
						"deviceHardwareVersion": "1.11",
						"deviceSn": "G1LQ6JB00631A",
						"deviceUpTime": "2019-03-14 20:52:03",
						"deviceInputConfig": null,
						"deviceProvision": "部署成功",
						"createDate": "2019-03-18 14:11:31",
						"modifyDate": "2019-03-18 18:53:25",
						"zones": [{
							"id": 2851,
							"oncZoneId": null,
							"oncZoneName": null,
							"ibnsZoneId": 1402,
							"ibnsZoneName": "1402",
							"ibnsZoneIds": "1/1402",
							"deploy": false
						}],
						"protocolConfigurations": [{
							"id": 3568,
							"name": "ssh",
							"certificateId": 1453,
							"ssh": {
								"id": 3115,
								"user": "ruijie",
								"password": "ruijie",
								"port": "22",
								"needsupwd": true,
								"protocolType": "SSH",
								"enableCommand": "enable",
								"enablePassword": "ruijie",
								"online": true
							}
						}, {
							"id": 3569,
							"name": "telnet",
							"certificateId": 1451,
							"telnet": {
								"id": 3165,
								"authMode": "USER_PASSWORD",
								"user": "ruijie",
								"password": "ruijie",
								"timeout": 0,
								"needsupwd": true,
								"port": 23,
								"suprompt": "Password:",
								"prompt": ">",
								"superPassword": "ruijie",
								"online": false
							}
						}, {
							"id": 3570,
							"name": "netconf",
							"certificateId": 1454,
							"netconf": {
								"id": 3213,
								"user": "ruijie",
								"password": "ruijie",
								"port": "830",
								"useTcpOnly": false,
								"online": true,
								"version": "1.1"
							}
						}, {
							"id": 3571,
							"name": "openflow",
							"openflow": {
								"id": 3263,
								"oid": "500833834804",
								"online": true,
								"version": "OFP_VERSION_1_3"
							}
						}, {
							"id": 3567,
							"name": "snmpv2",
							"certificateId": 1452,
							"snmpV2": {
								"id": 3065,
								"port": "161",
								"community": "public",
								"online": true
							}
						}],
						"zonesValue": "默认区域-zone_1",
						"area_name": "默认区域-zone_1",
						"location": null,
						"device_ip": "172.18.165.35",
						"device_model": "S2910-48GT4XS-E"
					}, {
						"id": 2919,
						"deviceId": "b310d653-56fe-46a6-81f1-1c8965c51295",
						"deviceName": "cisco",
						"deviceIp": "172.18.137.132",
						"deviceVtepIp": null,
						"deviceType": "other",
						"deviceOnline": true,
						"deviceMac": "4c:77:6d:94:0c:00",
						"devicePhyarea": null,
						"deviceLocation": null,
						"deviceManufactor": "CISCO",
						"deviceModel": "C9407R",
						"deviceRole": "gacc",
						"deviceNetRole": "other",
						"deviceSoftwareVersion": null,
						"deviceHardwareVersion": "V01",
						"deviceSn": "FXS2202Q11X",
						"deviceUpTime": "2019-03-15 08:50:59",
						"deviceInputConfig": null,
						"deviceProvision": "部署成功",
						"createDate": "2019-03-18 15:17:20",
						"modifyDate": "2019-03-18 16:13:25",
						"zones": [{
							"id": 2851,
							"oncZoneId": null,
							"oncZoneName": null,
							"ibnsZoneId": 1402,
							"ibnsZoneName": "1402",
							"ibnsZoneIds": "1/1402",
							"deploy": false
						}],
						"protocolConfigurations": [{
							"id": 3585,
							"name": "snmpv2",
							"certificateId": 1452,
							"snmpV2": {
								"id": 3069,
								"port": "161",
								"community": "public",
								"online": true
							}
						}, {
							"id": 3586,
							"name": "ssh",
							"certificateId": 1453,
							"ssh": {
								"id": 3119,
								"user": "ruijie",
								"password": "ruijie",
								"port": "22",
								"needsupwd": true,
								"protocolType": "SSH",
								"enableCommand": "enable",
								"enablePassword": "ruijie",
								"online": false
							}
						}, {
							"id": 3587,
							"name": "telnet",
							"certificateId": 1451,
							"telnet": {
								"id": 3169,
								"authMode": "USER_PASSWORD",
								"user": "ruijie",
								"password": "ruijie",
								"timeout": 0,
								"needsupwd": true,
								"port": 23,
								"suprompt": "Password:",
								"prompt": ">",
								"superPassword": "ruijie",
								"online": false
							}
						}],
						"zonesValue": "默认区域-zone_1",
						"area_name": "默认区域-zone_1",
						"location": null,
						"device_ip": "172.18.137.132",
						"device_model": "C9407R"
					}]
				}
			}
		},
		methods: {
			send() {
				let temp = {};
				temp.name = "aaa";
				temp.url = "sssssss";
				document.getElementById("iframe").contentWindow.postMessage(this.data, "*");
				console.log("rrrrrrrrrrrrrrrr")
			}
		},
	}
</script>

<style lang="scss">
</style>
