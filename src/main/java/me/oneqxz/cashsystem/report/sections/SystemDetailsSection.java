package me.oneqxz.cashsystem.report.sections;

import lombok.extern.log4j.Log4j2;
import me.sgx.GeometryDash;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
public class SystemDetailsSection implements ICrashSection {

    @Override
    public String formatSection(Throwable crush) {
        StringBuilder sb = new StringBuilder();

        sb.append("-- System Details --\n");
        sb.append("Details:\n");
        sb.append(addDetail("Geometry Dash Version", GeometryDash.VERSION::getAsString));
        sb.append(addDetail("Geometry Dash Version ID", () -> String.valueOf(GeometryDash.VERSION.getVersion())));
        sb.append(addDetail("Operating System", () -> "%s (%s) version %s".formatted(System.getProperty("os.name"), System.getProperty("os.arch"), System.getProperty("os.version"))));
        sb.append(addDetail("Java Version", () -> "%s, %s".formatted(System.getProperty("java.version"), System.getProperty("java.vendor"))));
        sb.append(addDetail("Java VM Version", () -> "%s (%s), %s".formatted(System.getProperty("java.vm.name"), System.getProperty("java.vm.info"), System.getProperty("java.vm.vendor"))));
        sb.append(addDetail("Memory", () -> {
            Runtime runtime = Runtime.getRuntime();
            long l = runtime.maxMemory();
            long m = runtime.totalMemory();
            long n = runtime.freeMemory();
            long o = l / 1024L / 1024L;
            long p = m / 1024L / 1024L;
            long q = n / 1024L / 1024L;
            return "%s bytes (%s MB) / %s bytes (%s MB) up to %s bytes (%s MB)".formatted(n, q, m, p, l, o);
        }));
        sb.append(addDetail("CPUs", () -> String.valueOf(Runtime.getRuntime().availableProcessors())));
        sb.append(addDetail("JVM Flags", () -> {
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

            List<String> list = runtimeMXBean.getInputArguments().stream().filter(string -> string.startsWith("-X")).toList();
            return String.format("%d total; %s", list.size(), String.join(" ", list));
        }));

        return sb.toString();
    }
}
