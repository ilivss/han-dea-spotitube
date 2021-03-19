package han.oose.dea.persistence.mappers;

public interface IDataMapper <RS, DO> {
    DO toDomainObject (RS resultSet);
    RS toResultSet (DO domainObject);
}
