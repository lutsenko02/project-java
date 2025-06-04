package dao;

import domain.District;
import domain.Region;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RegionDbDAO implements RepositoryDAO<Region> {
    
    // SQL-запросы к таблице regions
    private static final String SELECT_ALL_REGIONS = 
        "SELECT r.id, r.region_name, r.region_area, r.region_city, r.region_head, " +
        "d.id as district_id, d.namedistrict, d.district_area, d.district_year, d.district_number_of_people " +
        "FROM regions r LEFT JOIN districts d ON r.district_id = d.id ORDER BY r.region_name ASC";
    
    private static final String SELECT_REGION_BY_ID = 
        "SELECT r.id, r.region_name, r.region_area, r.region_city, r.region_head, " +
        "d.id as district_id, d.namedistrict, d.district_area, d.district_year, d.district_number_of_people " +
        "FROM regions r LEFT JOIN districts d ON r.district_id = d.id WHERE r.id = ?";
    
    private static final String INSERT_REGION = 
        "INSERT INTO regions(region_name, region_area, region_city, region_head, district_id) " +
        "VALUES(?, ?, ?, ?, ?)";
    
    private static final String UPDATE_REGION = 
        "UPDATE regions SET region_name = ?, region_area = ?, region_city = ?, " +
        "region_head = ?, district_id = ? WHERE id = ?";
    
    private static final String DELETE_REGION = 
        "DELETE FROM regions WHERE id = ?";
    
    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Region region) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_REGION, new String[] { "id" })) {
            
            pst.setString(1, region.getregionName());
            pst.setString(2, region.getregionArea());
            pst.setString(3, region.getregionCity());
            pst.setString(4, region.getregionHead());
            
            District district = region.getDistrict();
            if (district != null && district.getId() != null) {
                pst.setLong(5, district.getId());
            } else {
                pst.setNull(5, Types.BIGINT);
            }
            
            pst.executeUpdate();
            
            ResultSet gk = pst.getGeneratedKeys();
            Long id = -1L;
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
            
        } catch (Exception e) {
            throw new Exception("Error inserting region", e);
        }
    }

    @Override
    public void update(Region region) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_REGION)) {
            
            pst.setString(1, region.getregionName());
            pst.setString(2, region.getregionArea());
            pst.setString(3, region.getregionCity());
            pst.setString(4, region.getregionHead());
            
            District district = region.getDistrict();
            if (district != null && district.getId() != null) {
                pst.setLong(5, district.getId());
            } else {
                pst.setNull(5, Types.BIGINT);
            }
            
            pst.setLong(6, region.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Error updating region with id: " + region.getId(), e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_REGION)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Error deleting region with id: " + id, e);
        }
    }

    @Override
    public Region
    findById(Long id) throws Exception {
        Region region = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_REGION_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                region = fillRegion(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception("Error finding region with id: " + id, e);
        }
        return region;
    }

    @Override
    public List<Region> findAll() throws Exception {
        List<Region> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_REGIONS);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                list.add(fillRegion(rs));
            }
            
        } catch (Exception e) {
            throw new Exception("Error finding all regions", e);
        }
        return list;
    }

    private Region fillRegion(ResultSet rs) throws SQLException {
        Region region = new Region();
        region.setId(rs.getLong("id"));
        region.setregionName(rs.getString("region_name"));
        region.setregionArea(rs.getString("region_area"));
        region.setregionCity(rs.getString("region_city"));
        region.setregionHead(rs.getString("region_head"));
        
        // Fill district if exists
        if (rs.getObject("district_id") != null) {
            District district = new District();
            district.setId(rs.getLong("district_id"));
            district.setnamedistrict(rs.getString("namedistrict"));
            district.setDistrictArea(rs.getString("district_area"));
            district.setDistrictYear(rs.getString("district_year"));
            district.setDistrictNumberOfPeople(rs.getInt("district_number_of_people"));
            region.setDistrict(district);
        }
        
        return region;
    }

    public Region findById(Long id, List<Region> regions) {
        if (regions != null) {
            for (Region region : regions) {
                if (region.getId().equals(id)) {
                    return region;
                }
            }
        }
        return null;
    }
}