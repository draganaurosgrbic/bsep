package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.KeyPurposeId;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Extensions {

    private static final ASN1ObjectIdentifier id_kp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean basicConstraints; 

    private Integer keyUsage;

    @ElementCollection
    private Set<String> keyPurposeIds;

    @JsonIgnore
    public Set<KeyPurposeId> getEntityKeyPurposeIds() {
        return keyPurposeIds.stream()
	        .map(kpi -> KeyPurposeId.getInstance(id_kp.branch(kpi)))
	        .collect(Collectors.toSet());
    }

}
