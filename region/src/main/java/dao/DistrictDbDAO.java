package dao;
import domain.District;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DistrictDbDAO implements RepositoryDAO<District> {
    
    // SQL-запросы к таблице districts
    private static final String SELECT_ALL_DISTRICTS = 
        "SELECT id, namedistrict, district_area, district_year, district_number_of_people " +
        "FROM districts ORDER BY namedistrict ASC";
    
    private static final String SELECT_DISTRICT_BY_ID = 
        "SELECT id, namedistrict, district_area, district_year, district_number_of_people " +
        "FROM districts WHERE id = ?";
    
    private static final String INSERT_DISTRICT = 
        "INSERT INTO districts(namedistrict, district_area, district_year, district_number_of_people) " +
        "VALUES(?, ?, ?, ?)";
    
    private static final String UPDATE_DISTRICT = 
        "UPDATE districts SET namedistrict = ?, district_area = ?, district_year = ?, " +
        "district_number_of_people = ? WHERE id = ?";
    
    private static final String DELETE_DISTRICT = 
        "DELETE FROM districts WHERE id = ?";
    
    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(District district) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_DISTRICT, new String[] { "id" })) {
            
            pst.setString(1, district.getNameDistrict());
            pst.setString(2, district.getDistrictArea());
            pst.setString(3, district.getDistrictYear());
            pst.setInt(4, district.getDistrictNumberOfPeople());
            pst.executeUpdate();
            
            ResultSet gk = pst.getGeneratedKeys();
            Long id = -1L;
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
            
        } catch (Exception e) {
            throw new Exception("Error inserting district", e);
        }
    }

    @Override
    public void update(District district) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_DISTRICT)) {
            
            pst.setString(1, district.getNameDistrict());
            pst.setString(2, district.getDistrictArea());
            pst.setString(3, district.getDistrictYear());
            pst.setInt(4, district.getDistrictNumberOfPeople());
            pst.setLong(5, district.getId());
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Error updating district with id: " + district.getId(), e);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_DISTRICT)) {
            
            pst.setLong(1, id);
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new Exception("Error deleting district with id: " + id, e);
        }
    }

    @Override
    public District findById(Long id) throws Exception {
        District district = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_DISTRICT_BY_ID)) {
            
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                district = fillDistrict(rs);
            }
            rs.close();
            
        } catch (Exception e) {
            throw new Exception("Error finding district with id: " + id, e);
        }
        return district;
    }

    @Override
    public List<District> findAll() throws Exception {
        List<District> list = new LinkedList<>();
        try (Connection con = getConnection();
        		PreparedStatement pst = con.prepareStatement(SELECT_ALL_DISTRICTS);
                ResultSet rs = pst.executeQuery()) {
               
               while (rs.next()) {
                   list.add(fillDistrict(rs));
               }
               
           } catch (Exception e) {
               throw new Exception("Error finding all districts", e);
           }
           return list;
       }

       private District fillDistrict(ResultSet rs) throws SQLException {
           District district = new District();
           district.setId(rs.getLong("id"));
           district.setnamedistrict(rs.getString("namedistrict"));
           district.setDistrictArea(rs.getString("district_area"));
           district.setDistrictYear(rs.getString("district_year"));
           district.setDistrictNumberOfPeople(rs.getInt("district_number_of_people"));
           return district;
       }

       public District findById(Long id, List<District> districts) {
           if (districts != null) {
               for (District district : districts) {
                   if (district.getId().equals(id)) {
                       return district;
                   }
               }
           }
           return null;
       }
   }