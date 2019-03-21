import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.HashMap;
import java.util.Map;

public class Position{

    @QuerySqlField(index = true)
    private String positionId;

    private Map<String,Object> data;

    public Position(String positionId) {
        this.positionId = positionId;
        data = new HashMap<String, Object>();
    }


    /** Organization name (indexed). */
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public boolean addData(String key, Object value)
    {
        if(data.containsKey(key))
        {
            return  false;
        }

        data.put(key, value);
        return true;
    }

    public Object getData(String key)
    {
        return data.get(key);
    }

}