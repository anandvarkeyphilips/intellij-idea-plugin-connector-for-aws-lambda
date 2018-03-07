package io.github.satr.idea.plugin.connector.la.ui;

import com.amazonaws.regions.Regions;
import io.github.satr.common.CompositLogger;
import io.github.satr.common.Logger;
import io.github.satr.idea.plugin.connector.la.models.ConnectorModel;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractConnectorPresenter {
    private ConnectorModel connectorModel;
    private final CompositLogger logger = new CompositLogger();

    public Logger getLogger() {
        return logger;
    }

    protected void shutdownConnectorModel() {
        if(connectorModel != null) {
            connectorModel.shutdown();
        }
    }

    @NotNull
    private ConnectorModel createConnectorModel(Regions region, String profileName) {
        return new ConnectorModel(region, profileName);
    }

    protected ConnectorModel getConnectorModel() {
        if (connectorModel != null) {
            return connectorModel;
        }
        return connectorModel = createConnectorModel(getLastSelectedRegion(), getLastSelectedCredentialProfile());
    }

    protected void reCreateConnectorModel(Regions region, String credentialProfile) {
        shutdownConnectorModel();
        connectorModel = createConnectorModel(region, credentialProfile);
    }

    public void addLogger(Logger logger) {
        this.logger.addLogger(logger);
    }

    @NotNull
    protected abstract Regions getLastSelectedRegion();

    @NotNull
    protected abstract String getLastSelectedCredentialProfile();
}
