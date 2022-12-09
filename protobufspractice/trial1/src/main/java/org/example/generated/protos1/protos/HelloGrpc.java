package org.example.generated.protos1.protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: protofile1.proto")
public final class HelloGrpc {

  private HelloGrpc() {}

  public static final String SERVICE_NAME = "org.example.package1.protos.Hello";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHelloRequest,
      org.example.generated.protos1.protos.SayHelloResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = org.example.generated.protos1.protos.SayHelloRequest.class,
      responseType = org.example.generated.protos1.protos.SayHelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHelloRequest,
      org.example.generated.protos1.protos.SayHelloResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHelloRequest, org.example.generated.protos1.protos.SayHelloResponse> getSayHelloMethod;
    if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
          HelloGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<org.example.generated.protos1.protos.SayHelloRequest, org.example.generated.protos1.protos.SayHelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "org.example.package1.protos.Hello", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.generated.protos1.protos.SayHelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.generated.protos1.protos.SayHelloResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHiRequest,
      org.example.generated.protos1.protos.SayHiResponse> getSayHiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHi",
      requestType = org.example.generated.protos1.protos.SayHiRequest.class,
      responseType = org.example.generated.protos1.protos.SayHiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHiRequest,
      org.example.generated.protos1.protos.SayHiResponse> getSayHiMethod() {
    io.grpc.MethodDescriptor<org.example.generated.protos1.protos.SayHiRequest, org.example.generated.protos1.protos.SayHiResponse> getSayHiMethod;
    if ((getSayHiMethod = HelloGrpc.getSayHiMethod) == null) {
      synchronized (HelloGrpc.class) {
        if ((getSayHiMethod = HelloGrpc.getSayHiMethod) == null) {
          HelloGrpc.getSayHiMethod = getSayHiMethod = 
              io.grpc.MethodDescriptor.<org.example.generated.protos1.protos.SayHiRequest, org.example.generated.protos1.protos.SayHiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "org.example.package1.protos.Hello", "SayHi"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.generated.protos1.protos.SayHiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.generated.protos1.protos.SayHiResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloMethodDescriptorSupplier("SayHi"))
                  .build();
          }
        }
     }
     return getSayHiMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloStub newStub(io.grpc.Channel channel) {
    return new HelloStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HelloBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HelloFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class HelloImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(org.example.generated.protos1.protos.SayHelloRequest request,
        io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sends another greeting
     * </pre>
     */
    public void sayHi(org.example.generated.protos1.protos.SayHiRequest request,
        io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHiMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.example.generated.protos1.protos.SayHelloRequest,
                org.example.generated.protos1.protos.SayHelloResponse>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayHiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.example.generated.protos1.protos.SayHiRequest,
                org.example.generated.protos1.protos.SayHiResponse>(
                  this, METHODID_SAY_HI)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloStub extends io.grpc.stub.AbstractStub<HelloStub> {
    private HelloStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(org.example.generated.protos1.protos.SayHelloRequest request,
        io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sends another greeting
     * </pre>
     */
    public void sayHi(org.example.generated.protos1.protos.SayHiRequest request,
        io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHiMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloBlockingStub extends io.grpc.stub.AbstractStub<HelloBlockingStub> {
    private HelloBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.example.generated.protos1.protos.SayHelloResponse sayHello(org.example.generated.protos1.protos.SayHelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sends another greeting
     * </pre>
     */
    public org.example.generated.protos1.protos.SayHiResponse sayHi(org.example.generated.protos1.protos.SayHiRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHiMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class HelloFutureStub extends io.grpc.stub.AbstractStub<HelloFutureStub> {
    private HelloFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.generated.protos1.protos.SayHelloResponse> sayHello(
        org.example.generated.protos1.protos.SayHelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sends another greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.generated.protos1.protos.SayHiResponse> sayHi(
        org.example.generated.protos1.protos.SayHiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHiMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_HI = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((org.example.generated.protos1.protos.SayHelloRequest) request,
              (io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHelloResponse>) responseObserver);
          break;
        case METHODID_SAY_HI:
          serviceImpl.sayHi((org.example.generated.protos1.protos.SayHiRequest) request,
              (io.grpc.stub.StreamObserver<org.example.generated.protos1.protos.SayHiResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.generated.protos1.protos.HelloProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Hello");
    }
  }

  private static final class HelloFileDescriptorSupplier
      extends HelloBaseDescriptorSupplier {
    HelloFileDescriptorSupplier() {}
  }

  private static final class HelloMethodDescriptorSupplier
      extends HelloBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayHiMethod())
              .build();
        }
      }
    }
    return result;
  }
}
