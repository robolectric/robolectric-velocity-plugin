package org.robolectric.templates;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

/**
 * Logging adapter for Velocity.
 */
public class LogHandler implements LogChute {
  private final AbstractMojo mojo;

  public LogHandler(AbstractMojo mojo) {
    this.mojo = mojo;
  }

  public void init(RuntimeServices runtimeServices) throws Exception {
  }

  public boolean isLevelEnabled(int level) {
    if (level == DEBUG_ID && mojo.getLog().isDebugEnabled()) {
      return true;
    } else if (level == INFO_ID && mojo.getLog().isInfoEnabled()) {
      return true;
    } else if (level == WARN_ID && mojo.getLog().isWarnEnabled()) {
      return true;
    } else if (level == ERROR_ID && mojo.getLog().isErrorEnabled()) {
      return true;
    }
    return false;
  }

  public void log(int level, String content) {
    if (isLevelEnabled(level))
      switch (level) {
        case DEBUG_ID:
          mojo.getLog().debug(content);
          break;
        case INFO_ID:
          mojo.getLog().info(content);
          break;
        case WARN_ID:
          mojo.getLog().warn(content);
          break;
        case ERROR_ID:
          mojo.getLog().error(content);
          break;
      }
  }

  public void log(int level, String content, Throwable throwable) {
    if (isLevelEnabled(level))
      switch (level) {
        case DEBUG_ID:
          mojo.getLog().debug(content, throwable);
          break;
        case INFO_ID:
          mojo.getLog().info(content, throwable);
          break;
        case WARN_ID:
          mojo.getLog().warn(content, throwable);
          break;
        case ERROR_ID:
          mojo.getLog().error(content, throwable);
          break;
      }
  }
}
