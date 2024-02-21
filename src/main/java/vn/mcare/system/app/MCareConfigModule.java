package vn.mcare.system.app;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.ForcedType;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.mcare.system.common.constant.config.JooqGeneratorConfig;
import vn.mcare.system.common.constant.config.ModuleConfig;
import vn.mcare.system.common.constant.config.SSHTunnelConfig;
import vn.mcare.system.common.constant.start.DatabaseConfig;
import vn.mcare.system.common.context.ExceptionTranslator;
import vn.mcare.system.common.context.SpringConnectionProvider;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MCareConfigModule {

    private final ModuleConfig moduleConfig;
    private HikariDataSource dataSource;

    @Bean
    public org.jooq.Configuration getConfiguration() {
        Settings settings = new Settings();
        settings.setExecuteLogging(false);

        return new DefaultConfiguration()
                .set(new SpringConnectionProvider(dataSource))
                .set(
                        SQLDialect
                                .MYSQL) // TODO need a factory to produce dialect according to dataSourceClassName
                .set(new DefaultExecuteListenerProvider(new ExceptionTranslator(dataSource)))
                .set(settings);
    }

    private void initSSHTunnel() {
        try {
            SSHTunnelConfig ssh = moduleConfig.getSshTunnel();
            final JSch jsch = new JSch();
            Session session = jsch.getSession(ssh.getSshUser(), ssh.getSshHost(), ssh.getSshPort());
            session.setPassword(ssh.getSshPassword());

            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            session.setPortForwardingL(ssh.getLocalPort(), ssh.getDatabaseHost(), ssh.getDatabasePort());
            log.info("init ssh tunnel success");
        } catch (Exception e) {
            log.error("init ssh tunnel error: {}", e);
        }
    }

    private void genJooq() {
        JooqGeneratorConfig config = moduleConfig.getJooqGenerator();

        ForcedType timestamp = new ForcedType();
        timestamp.setUserType("java.lang.Long");
        timestamp.setBinding("vn.mcare.system.common.converter.TimestampToLongBinding");
        timestamp.setTypes("timestamp");

        org.jooq.meta.jaxb.Configuration configuration =
                new org.jooq.meta.jaxb.Configuration()
                        .withJdbc(
                                new Jdbc()
                                        .withDriver(config.getJdbcDriver())
                                        .withUrl(config.getJdbcUrl())
                                        .withUser(config.getUser())
                                        .withPassword(config.getPassword()))
                        .withGenerator(
                                new Generator()
                                        .withDatabase(
                                                new Database()
                                                        .withName(config.getJooqDatabaseName())
                                                        .withIncludes(config.getIncludes())
                                                        .withExcludes(config.getExcludes())
                                                        .withInputSchema(config.getInputSchema())
                                                        .withForcedTypes(timestamp))
                                        .withGenerate(
                                                new Generate().withRecords(true).withFluentSetters(true).withDaos(false))
                                        .withTarget(
                                                new Target()
                                                        .withPackageName(config.getPackageName())
                                                        .withDirectory(config.getDirectory())));
        try {
            GenerationTool.generate(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return initJooq(moduleConfig).getConnection();
    }

    @Bean
    public DataSource initJooq(ModuleConfig moduleConfig) {
        //    initSSHTunnel();
        //        genJooq(); //TODO open when you need generate table
        DatabaseConfig config = moduleConfig.getDatabaseConfig();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(config.getClassDriver());
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setConnectionTimeout(10000);
        // disable this one because we will use our own TransactionalManager
        hikariConfig.setAutoCommit(true);
        log.info("Init connection to db success");
        dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
