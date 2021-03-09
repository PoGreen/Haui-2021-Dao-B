package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.District;
import com.haui.demo.models.entities.Province;
import com.haui.demo.models.entities.Ward;
import com.haui.demo.models.responses.DistrictRp;
import com.haui.demo.models.responses.ProvinceRp;
import com.haui.demo.models.responses.WardRp;
import com.haui.demo.repositories.DistrictRepository;
import com.haui.demo.repositories.ProvinceRepository;
import com.haui.demo.repositories.WardRepository;
import com.haui.demo.services.ILocationService;
import com.haui.demo.services.mappers.LocationMapper;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public ResponseEntity<SystemResponse<Object>> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceRp> provinceRps = locationMapper.mapToProvinceRps(provinces);
        Map<String,Object> result = new HashMap<>();
        result.put(Global.PROVINCES,provinceRps);
        return Response.ok(result);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getDistrictsByProvince(int provinceId) {
        Province province = provinceRepository.findById(provinceId).orElse(null);
        if(Objects.isNull(province)){
            return Response.badRequest(StringResponse.PROVINCE_IS_FAKE);
        }
        List<District> districts = districtRepository.findByProvince(provinceId);
        List<DistrictRp> districtRps = locationMapper.mapToDistrictRps(districts);
        Map<String,Object> result = new HashMap<>();
        result.put(Global.DISTRICTS,districtRps);
        return Response.ok(result);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getWardsByDistrict(int districtId) {
        District district = districtRepository.findById(districtId).orElse(null);
        if(Objects.isNull(district)){
            return Response.badRequest(StringResponse.DISTRICT_IS_FAKE);
        }
        List<Ward> wards = wardRepository.findByDistrict(districtId);
        List<WardRp> wardRps = locationMapper.mapToWardRps(wards);
        Map<String,Object> result = new HashMap<>();
        result.put(Global.WARDS,wardRps);
        return Response.ok(result);
    }

    @Override
    public String getLocationByWard(int wardId) {
        Ward ward = wardRepository.findById(wardId).orElse(null);
        if (!Objects.isNull(ward)) {
            District district = districtRepository.findById(ward.getDistrict()).orElse(null);
            Province province = provinceRepository.findById(district.getProvince()).orElse(null);
            return province.getName() + " - " + district.getName() + " - " + ward.getName();
        }
        return "";
    }
}
